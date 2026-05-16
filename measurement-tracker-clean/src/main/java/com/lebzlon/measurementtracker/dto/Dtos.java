package com.lebzlon.measurementtracker.dto;
import com.lebzlon.measurementtracker.entity.*; import io.swagger.v3.oas.annotations.media.Schema; import jakarta.validation.constraints.*; import java.math.BigDecimal; import java.time.Instant;
public class Dtos {
@Schema(description="register") public record RegisterRequest(@NotBlank String username,@Size(min=6) String password){}
public record LoginRequest(@NotBlank String username,@NotBlank String password){}
public record AuthResponse(String accessToken){}
public record UserResponse(Long id,String username,Instant createdAt){}
public record MeterRequest(@NotBlank String serialNumber,@NotNull MeterType type,String title){}
public record MeterResponse(Long id,String serialNumber,MeterType type,String title,boolean active){}
public record ManualReadingRequest(@NotNull Long meterId,@NotNull Instant timestamp,@DecimalMin("0.0") BigDecimal value){}
public record ReadingResponse(Long id,Long meterId,Instant timestamp,BigDecimal value,ReadingSource source,String imagePath,String ocrRawText){}
public record OcrTaskResponse(Long id,Long meterId,String imagePath,Instant timestamp,OcrTaskStatus status,String rawText,BigDecimal extractedValue,String errorMessage){}
}
