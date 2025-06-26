;这段代码是一个16位的x86汇编程序，用作操作系统的引导扇区
;引导扇区是计算机启动时BIOS首先加载和执行的512字节的代码
org 7c00h ;告诉汇编器代码加载到内存地址Ox7C00处
;org是汇编指令，指定程序加载的起始地址
mov ax, cs;将代码段寄存器(CS)的值赋给AX
mov es, ax;将ES段寄存器设为与CS相同的值
mov ax, msg;将字符串"Hello world..."的地址赋给AX
mov bp, ax;BP是INT 10H的参数，指向要显示的字符串
mov cx, msgLen;CX是字符串长度
mov ax, 1301h; AH=13h（BIOS显示服务功能号），AL=01h（显示方式：字符串含属性）
mov bx, 000fh;BH=0（页号），BL=0Fh（颜色：白色前景，黑色背景）
mov dl, 0; DL=0（显示在第0列）
int 10h;调用BIOS中断10H（显示服务）

msg:db"Hello world, welcome to OS!";定义字符串
msgLen:equ $ - msg;计算字符串的长度
times 510 - ($ -$$) db 0;用0填充剩余空间，使代码达到510字节
dw 0aa55h;在第511-512字节写入引导标志0xAA55