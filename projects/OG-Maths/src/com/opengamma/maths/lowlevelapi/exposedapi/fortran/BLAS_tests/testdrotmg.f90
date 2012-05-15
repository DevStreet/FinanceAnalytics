!
! Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
! 
! Please see distribution for license.
!
program testdrotmg
implicit none
double precision:: dd1, dd2, dx1, dy2, dPARAM(5)
double precision:: a, b, DH11, DH12, DH21, DH22
integer:: FLAG
double precision:: H(2,2), input(2);

external drotmg

dd1=1
dd2=2
dx1=3
dy2=4
dPARAM=0;
H=0;

a=dsqrt(dd1)*dx1
b=dsqrt(dd2)*dy2
input(1)=a
input(2)=b

call drotmg(dd1,dd2,dx1,dy2,dPARAM)

print*,"dd1,dd2,dx1,dy2",dd1,dd2,dx1,dy2

print*,"dPARAM"
print*,dPARAM

print*,""
DH11=dPARAM(2)
DH21=dPARAM(3)
DH12=dPARAM(4)
DH22=dPARAM(5)
FLAG=dPARAM(1)
select case(FLAG)
        case(-1)
                H(1,1)=DH11
                H(1,2)=DH12
                H(2,1)=DH21
                H(2,2)=DH22
        case(0)
                H(1,1)=1.0d0
                H(1,2)=DH12
                H(2,1)=DH21
                H(2,2)=1.0d0
        case(1)
                H(1,1)=DH11
                H(1,2)=1.0d0
                H(2,1)=-1.0d0
                H(2,2)=DH22
        case(-2)
                H(1,1)=1.0d0
                H(1,2)=0.0d0
                H(2,1)=0.0d0
                H(2,2)=1.0d0
        case default
              STOP("Error in results from drotmg()")
end select

print*,"Reconstruct"
print*,"H="
print*,H(1,:)
print*,H(2,:)
print*,"input",input
print*,"H*input"
print*,matmul(H,input)
print*,"dd1",dd1
print*,"dd2",dd2

end program testdrotmg