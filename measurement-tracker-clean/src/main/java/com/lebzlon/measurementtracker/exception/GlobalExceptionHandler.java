package com.lebzlon.measurementtracker.exception;
import jakarta.servlet.http.HttpServletRequest; import org.springframework.http.*; import org.springframework.security.access.AccessDeniedException; import org.springframework.web.bind.MethodArgumentNotValidException; import org.springframework.web.bind.annotation.*; import java.time.Instant; import java.util.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> val(MethodArgumentNotValidException e, HttpServletRequest r){ Map<String,Object> body=base(400,"Validation Error",e.getMessage(),r); body.put("fieldErrors", e.getBindingResult().getFieldErrors().stream().map(f->Map.of("field",f.getField(),"message",f.getDefaultMessage())).toList()); return ResponseEntity.badRequest().body(body);}    
    @ExceptionHandler({NotFoundException.class,ApiException.class,AccessDeniedException.class,Exception.class}) ResponseEntity<?> any(Exception e,HttpServletRequest r){ int s=e instanceof NotFoundException?404:e instanceof AccessDeniedException?403:400; return ResponseEntity.status(s).body(base(s,e.getClass().getSimpleName(),e.getMessage(),r)); }
    private Map<String,Object> base(int s,String err,String msg,HttpServletRequest r){ return new LinkedHashMap<>(Map.of("timestamp", Instant.now(),"status",s,"error",err,"message",msg,"path",r.getRequestURI()));}
}
