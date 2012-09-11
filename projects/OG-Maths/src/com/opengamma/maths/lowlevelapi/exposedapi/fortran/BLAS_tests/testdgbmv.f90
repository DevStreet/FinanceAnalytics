!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdgbmv
implicit none
integer:: n, m, incx, incy, i
parameter(m=6, n=5, incx=1,incy=1)
integer, parameter:: ku = 1 , kl = 2
integer:: kup1, k, j
double precision:: A(m,n), AB(kl+ku+1,n),x(n), y(m), alpha, beta
character(2):: length
external dgbmv
write(length,"(I2)")n

do j = 1,n
        do i = 1,m
                A(i,j) = (i-1)*n+j
        enddo
enddo

!e A_ij is stored in AB(ku+1+i-j,j)
AB=-1 !e doesn't need to be set but shows where unset elements are present
kup1 = ku + 1
do j=1,n
        k = kup1 - j
        do i=max(1,j-ku),min(m,j+kl)
                AB(k+i,j)=A(i,j)
        enddo
enddo

!e set x
do i = 1,n
        x(i)=i;
enddo

!e set y
do i = 1,m
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
print*,""
do i=1,kl+ku+1
        write(*,"(A3)",ADVANCE="NO")"AB="
        write(*,"("//trim(adjustl(length))//"(2X,F5.2))"),AB(i,:)
enddo

alpha=2d0
beta=5d0

call  dgbmv("n",m,n,kl,ku,alpha,AB,kl+ku+1,x,incx,beta,y,incy)

print*,""
print*,"output"
write(*,"(A2)")"y="
write(*,"("//trim(adjustl(length))//"(2X,F8.2))"),y

end program testdgbmv