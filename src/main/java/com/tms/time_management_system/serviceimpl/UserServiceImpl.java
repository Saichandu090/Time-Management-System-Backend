package com.tms.time_management_system.serviceimpl;

import com.tms.time_management_system.dto.JsonResponse;
import com.tms.time_management_system.dto.UserDTO;
import com.tms.time_management_system.dto.UserLogin;
import com.tms.time_management_system.dto.UserRegisterDTO;
import com.tms.time_management_system.mapper.UserMapper;
import com.tms.time_management_system.model.Measurement;
import com.tms.time_management_system.model.User;
import com.tms.time_management_system.repository.UserRepository;
import com.tms.time_management_system.service.MeasurementService;
import com.tms.time_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    private final BCryptPasswordEncoder ENCODER=new BCryptPasswordEncoder(12);
    private final UserMapper USERMAPPER=new UserMapper();

    @Autowired
    private MeasurementService measurementService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationContext context;

    @Override
    public ResponseEntity<JsonResponse> registerUser(UserRegisterDTO registerDTO)
    {
        User user=USERMAPPER.getUser(registerDTO);
        user.setPassword(ENCODER.encode(user.getPassword()));
        return UserMapper.convertUser(userRepository.save(user));
    }

    @Override
    public ResponseEntity<JsonResponse> loginUser(UserLogin userLogin)
    {
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(),userLogin.getPassword()));
        if(authentication.isAuthenticated())
        {
            Measurement rs=measurementService.userLoggedIn(userLogin.getEmail());
            UserDetails userDetails=context.getBean(MyUserDetailsService.class).loadUserByUsername(userLogin.getEmail());
            String token=jwtService.generateToken(userLogin.getEmail());
            String role=null;
            if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("USER")))
                role="USER";
            else if(userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN")))
                role="ADMIN";

            JsonResponse response=new JsonResponse(true,token, List.of(new UserDTO(userLogin.getEmail(),role,rs.getId())));
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(new JsonResponse(false,"Wrong User Credentials",null),HttpStatus.BAD_REQUEST);
    }
}
