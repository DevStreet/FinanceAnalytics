!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdsbmv
implicit none
integer::  n, incx, incy, k, lda
parameter(n=5, incx=1,incy=1, k=2, lda=k+1)
integer:: j, i, zm
double precision:: A(n,n), AB(k+1,n), x(n), y(n), alpha, beta
character(2):: length
external dsbmv
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

!e set x
do i = 1,n
        x(i)=i;
enddo

!e set y
do i = 1,n
        y(i)=10*((i-1)*2+2);
enddo

print*,"input"
print*,"x=",x
print*,"y=",y
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

alpha=2d0
beta=5d0

call  dsbmv("U",n,k,alpha,AB,lda,x,incx,beta,y,incy)

print*,""
print*,"output"
write(*,"(A2)")"y="
write(*,"("//trim(adjustl(length))//"(2X,F8.2))"),y

end program testdsbmv