    package com.example.inventoryservice.advice;

    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.Around;
    import org.aspectj.lang.annotation.Aspect;
    import org.aspectj.lang.annotation.Pointcut;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ProblemDetail;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.ErrorResponse;
    import org.springframework.web.bind.annotation.ExceptionHandler;
    import org.springframework.web.bind.annotation.RestControllerAdvice;

    @RestControllerAdvice
    @Aspect
    public class InventoryControllerAdvice {

        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder(exception, problemDetail).build();
            return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ErrorResponse> handleException(Exception exception) {
            ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
            ErrorResponse errorResponse = ErrorResponse.builder(exception, problemDetail).build();
            return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
        }

//        @Pointcut("execution(* com.example.inventoryservice.controller.InventoryController.*(..))")
//        public void inventoryControllerMethods() {
//
//        }
//        @Around("inventoryControllerMethods()")
//        public ResponseEntity<Object> handleInventoryControllerExceptions(ProceedingJoinPoint joinPoint) {
//            try {
//                return (ResponseEntity<Object>) joinPoint.proceed();
//            } catch (IllegalArgumentException ex) {
//                ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
//                ErrorResponse errorResponse = ErrorResponse.builder(ex, problemDetail).build();
//                return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
//            } catch (Throwable ex) {
//                ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
//                ErrorResponse errorResponse = ErrorResponse.builder(ex, problemDetail).build();
//                return new ResponseEntity<>(errorResponse, errorResponse.getStatusCode());
//            }
//        }
    }
