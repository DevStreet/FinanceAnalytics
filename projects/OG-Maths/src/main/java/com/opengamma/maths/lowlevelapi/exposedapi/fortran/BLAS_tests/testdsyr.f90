!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdsyr
implicit none
integer::  n, incx, incy, lda
parameter( n=5, incx=1, incy=2, lda=n)
integer:: j, i
double precision:: A(n,n), AS(n,n), x(n), alpha
character(2):: length
external dsyr
write(length,"(I2)")n

!e some data for A
do j = 1,n
        do i = 1,n
                A(i,j) = (i-1)*n+j
        enddo
enddo

AS=-1;

do i = 1,n
        do j = i,n
                AS(i,j) = A(i,j)
        enddo
enddo

!e set x
do i = 1,n
        x(i)=i;
enddo

print*,"input"
print*,"x=",x
print*,""
print*,""
do i=1,n
        write(*,"(A3)",ADVANCE="NO")"A ="
        write(*,"("//trim(adjustl(length))//"(2X,F5.2))"),A(i,:)
enddo
print*,""
do i=1,n
        write(*,"(A3)",ADVANCE="NO")"AS ="
        write(*,"("//trim(adjustl(length))//"(2X,F5.2))"),AS(i,:)
enddo
print*,""
alpha=2d0

call  dsyr("U",n,alpha,x,incx,AS,lda)

print*,""
print*,"output"
do i=1,n
        write(*,"(A3)",ADVANCE="NO")"A ="
        write(*,"("//trim(adjustl(length))//"(2X,F8.2))"),AS(i,:)
enddo
print*,""

end program testdsyr