!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdtbsv
implicit none
integer::  n, incx, k, lda
parameter(n=5, incx=1, k=2, lda=k+1)
integer:: j, i, zm
double precision:: A(n,n), x(n), AB(lda,n)
character(2):: length
external dtbsv
write(length,"(I2)")n

!e some data for A
do j = 1,n
        do i = 1,n
                A(i,j) = (i-1)*n+j
        enddo
enddo

AB=-1;
!e UPLO = "U"
do j = 1, n
        zm = k + 1 - j
        do i = max( 1, j - k ), j
                AB( zm + i, j ) = A( i, j )
        enddo
enddo

!e set x, these are especially selected so that on solution of Ax=b, x=vector of ones.
x(1)=6;
x(2)=24;
x(3)=42;
x(4)=39;
x(5)=25;

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
do i=1,lda
        write(*,"(A3)",ADVANCE="NO")"AB="
        write(*,"("//trim(adjustl(length))//"(2X,F5.2))"),AB(i,:)
enddo

call  dtbsv("U","N","N",n,k,AB,lda,x,incx)

print*,""
print*,"output"
write(*,"(A2)")"x="
write(*,"("//trim(adjustl(length))//"(2X,F8.2))"),x

end program testdtbsv