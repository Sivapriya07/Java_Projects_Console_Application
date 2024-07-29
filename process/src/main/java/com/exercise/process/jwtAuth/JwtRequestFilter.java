//package com.exercise.process.jwtAuth;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import com.exercise.process.Entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.CachingUserDetailsService;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//public class JwtRequestFilter extends OncePerRequestFilter{
//
//    @Autowired
//    private JwtUtil jwtUtil;
//    @Autowired
//    private CachingUserDetailsService userDetailsService;
//    @Autowired
//    private User user;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        final String authorizationheader = request.getHeader("authorization");
//
//        String username= null;
//        String jwt = null;
//
//        if (authorizationheader != null && authorizationheader.startsWith("bearer")){
//            jwt =authorizationheader.substring(7);
//            username = jwtUtil.extractUsername(jwt);
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication()==null) {
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
//
//
//
//            if (jwtUtil.validateToken(jwt, user)) {
//
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
//                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetails().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//            }
//
//        }
//    }
//
//    private class WebAuthenticationDetails {
//    }
//}
