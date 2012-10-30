!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdrot
implicit none
integer:: n, incx, incy, i
parameter(n=10,incx=1,incy=2)
double precision:: x(n), y(2*n), c, s
character(2):: length
parameter (c = dsqrt(3.d0)/2.d0, s=0.5d0)
external drot

do i = 1,n
        x(i)=i;
        y((i-1)*2+1)=10*((i-1)*2+1);
        y((i-1)*2+2)=10*((i-1)*2+2);
enddo

print*,"input"
print*,"x=",x
print*,"y=",y

call drot(n,x,incx,y,incy,c,s)

print*,""
print*,"output"
print*,"x=",x
write(length,"(I2)")n
write(*,"(A3 "//trim(adjustl(length))//"(2X,F6.2))"),"y= ",y

end program testdrot