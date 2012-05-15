!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdcopy
implicit none
integer:: n, incx, incy, i
parameter(n=10,incx=1,incy=2)
double precision:: x(n), y(2*n)
character(2):: length
external dcopy

do i = 1,n
        x(i)=i;
        y((i-1)*2+1)=10*((i-1)*2+1);
        y((i-1)*2+2)=10*((i-1)*2+2);
enddo

print*,"input"
print*,"x=",x
print*,"y=",y

call dcopy(n,x,incx,y,incy)

print*,""
print*,"output"
write(length,"(I2)")n
write(*,"(A3 "//trim(adjustl(length))//"(2X,F6.2))"),"x=",x
write(length,"(I2)")2*n
write(*,"(A3 "//trim(adjustl(length))//"(2X,F6.2))"),"y=",y

end program testdcopy