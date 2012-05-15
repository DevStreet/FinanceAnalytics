!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdrotm
implicit none
integer:: n, incx, incy, i
parameter(n=10,incx=1,incy=2)
double precision:: x(n), y(2*n), dPARAM(5)
character(2):: length
external drotm

do i = 1,n
        x(i)=i;
        y((i-1)*2+1)=10*((i-1)*2+1);
        y((i-1)*2+2)=10*((i-1)*2+2);
enddo

dPARAM(1)=1d0;
dPARAM(2)=0.375d0;
dPARAM(3)=0.d0;
dPARAM(4)=0.d0;
dPARAM(5)=0.75d0;


print*,"input"
print*,"x=",x
print*,"y=",y


call drotm(n,x,incx,y,incy,dPARAM)

print*,""
print*,"output"
write(length,"(I2)")n
write(*,"(A3 "//trim(adjustl(length))//"(2X,F6.2))"),"x=",x
write(length,"(I2)")2*n
write(*,"(A3 "//trim(adjustl(length))//"(2X,F6.2))"),"y=",y

end program testdrotm