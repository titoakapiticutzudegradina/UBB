import socket

s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

print("Enter the date: ");
date = input()

s.connect(("172.30.240.134",2123))

res = s.send(date.encode())
c = s.recv(1024)
print("The day is: " + c.decode())
s.close()
