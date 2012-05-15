!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdscal
implicit none
integer:: n, incx, i
parameter(n=5,incx=2)
double precision:: x(n*incx), alpha
character(2):: length
external dscal

do i = 1,n*incx
        x(i)=i;
enddo

alpha=2d0;

print*,"input"
print*,"x=",x

call dscal(n,alpha,x,incx)

print*,""
print*,"output"
write(length,"(I2)")n
write(*,"(A3 "//trim(adjustl(length))//"(2X,F6.2))"),"x=",x

end program testdscal