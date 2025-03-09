import socket
import sys
import select
import os


if __name__ == '__main__':
    if len(sys.argv) < 3:
        print("Usage: \n{} <hostname or IP address> <portno>".format(sys.argv[0]))
        sys.exit(1)

    host = sys.argv[1]
    port = int(sys.argv[2])

    #Try to resolve hostname to IP address
    try:
        ipaddr = socket.gethostbyname(host)
        print(f"{host} => {ipaddr} ip address")
    except socket.gaierror:
        print("Error getting the host address")
        sys.exit(1)

    #Create a socket
    try:
        cs = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        cs.connect((ipaddr,port))
    except socket.error as msg:
        print(f"Error creating or connecting socket: {msg}")
        sys.exit(1)

    #Set up select (for file descriptors)
    input_fds = [sys.stdin, cs]
    output_fds = []

    print(f"Connected to {host} on port {port}")

    while True:
        #Use select to wait for data on stdin or the socket
        read_fds, _, _ = select.select(input_fds, output_fds, [])

        for fd in read_fds:
            if fd == sys.stdin:
                #Read data from stdin(keyboard)
                buf = sys.stdin.readline()
                if buf:
                    #Send the data to the server
                    try:
                        cs.sendall(buf.encode())
                    except socket.error as msg:
                        print(f"Error sending data: {msg}")
                        sys.exit(1)
            elif fd == cs:
                #Read data from the server (arrival/departure of other clients)
                try:
                    data = cs.recv(256)
                    if not data:
                        print("Server has closed the connection... closing...")
                        cs.close()
                        sys.exit(0)
                    sys.stdout.write(data.decode())
                except socket.error as msg:
                    print(f"Error receiving data: {msg}")
                    sys.exit(1)
