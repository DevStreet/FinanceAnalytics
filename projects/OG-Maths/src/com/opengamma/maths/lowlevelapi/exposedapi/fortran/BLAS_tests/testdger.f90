!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdger
implicit none
integer::  m, n, incx, incy, lda
parameter(m=4, n=3, incx=1, incy=2, lda=m)
integer:: j, i
double precision:: A(m,n), x(m), y(2*n), alpha
character(2):: length
external dger
write(length,"(I2)")n

!e some data for A
do j = 1,n
        do i = 1,m
                A(i,j) = (i-1)*n+j
        enddo
enddo

!e set x
do i = 1,m
        x(i)=i;
enddo

!e set y
do i = 1,2*n
        y(i)=10*((i-1)*2+2);
enddo

print*,"input"
print*,"x=",x
print*,"y=",y
print*,""
print*,""
do i=1,m
        write(*,"(A3)",ADVANCE="NO")"A ="
        write(*,"("//trim(adjustl(length))//"(2X,F5.2))"),A(i,:)
enddo
print*,""

alpha=2d0

call  dger(m,n,alpha,x,incx,y,incy,A,lda)

print*,""
print*,"output"
do i=1,m
        write(*,"(A3)",ADVANCE="NO")"A ="
        write(*,"("//trim(adjustl(length))//"(2X,F8.2))"),A(i,:)
enddo
print*,""

end program testdger