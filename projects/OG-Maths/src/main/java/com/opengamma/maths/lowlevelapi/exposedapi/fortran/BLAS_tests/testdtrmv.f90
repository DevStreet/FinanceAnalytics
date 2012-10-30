!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdtrmv
implicit none
integer::  n, incx
parameter(n=5, incx=1)
integer:: j, i
double precision:: A(n,n), x(n), AT(n,n)
character(2):: length
external dtrmv
write(length,"(I2)")n

!e some data for A
do j = 1,n
        do i = 1,n
                A(i,j) = (i-1)*n+j
        enddo
enddo

AT=-1;
!e UPLO = "U"
do j = 1, n
        do i = 1,j
                AT(i,j) = A( i, j )
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
print*,""
do i=1,n
        write(*,"(A3)",ADVANCE="NO")"AT="
        write(*,"("//trim(adjustl(length))//"(2X,F5.2))"),AT(i,:)
enddo

call  dtrmv("U","N","N",n,AT,n,x,incx);

print*,""
print*,"output"
write(*,"(A2)")"x="
write(*,"("//trim(adjustl(length))//"(2X,F8.2))"),x

end program testdtrmv