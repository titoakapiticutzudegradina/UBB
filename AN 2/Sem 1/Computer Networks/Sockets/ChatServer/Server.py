import socket
import select
import sys

#Server variables
clients = {} #Stores connected clients
client_count = 0

def get_client_ip_and_port(client_socket):
    #Helper function to get client IP and port.
    addr = client_socket.getpeername()
    return addr[0], addr[1]

def send_to_all(msg, sender_socket):
    #Send message to all clients except the sender
    for client_socket in clients.values():
        if client_socket != sender_socket:
            try:
                client_socket.send(msg.encode())
            except:
               pass #Ignore errors if the client is disconnected

def handle_new_connection(listener_socket):
   #Accept a new connection and add it to the clients list
   client_socket, client_address = listener_socket.accept()
   clients[client_socket] = client_socket
   global client_count
   client_count += 1

   print(f"New connection from {client_address[0]}:{client_address[1]} on socket {client_socket.fileno()}")

   #Notify the new client of their connection
   welcome_msg = f"Hi - you are client {client_socket.fileno()} ({client_address[0]}:{client_address[1]}) connected to the server. There are {client_count} clients connected.\n"
   client_socket.send(welcome_msg.encode())

   #Broadcast the new client's connection to all others
   notify_msg = f"<{client_address[0]}:{client_address[1]} - [{client_socket.flieno()}]> connected.\n"
   send_to_all(notify_msg, client_socket)

def handle_client_data(client_socket):
    #Handle data received from a client
    try:
        msg = client_socket.recv(256).decode()
        if msg == '':
            raise ConnectionError('Client disconnected')
        print(f"Received message: {msg} from client {client_socket.fileno()}")

        if msg.strip().lower() == 'quit':
            #Client wants to quit
            client_ip, client_port = get_client_ip_and_port(client_socket)
            quit_msg = f"Request granted [{client_socket.fileno()}] - {client_ip}:{client_port}. Dsiconnecting...\n"
            client_socket.send(quit_msg.encode())
            disconnect_msg = f"<{client_ip}:{client_port} - [{client_socket.fileno()}]> disconnected. \n"
            send_to_all(disconnect_msg, client_socket)

            #Cleanup
            client_socket.close()
            del clients[client_socket]
            global client_count
            client_count -= 1
        else:
            #Broadcast the message to all clients
            broadcast_msg =f"<{client_socket.fileno()}> {msg}"
            send_to_all(broadcast_msg, client_socket)
    except (ConnectionError, socket.error) :
        #Handle client disconnection or error
        client_ip, client_port = get_client_ip_and_port(client_socket)
        print(f"<selectserver>: client {client_socket.fileno()} forcibly hung up")
        client_socket.close()
        del clients[client_socket]
        #global client_count
        client_count -= 1
        disconnect_msg = f"<{client_ip}:{client_port} - [{client_socket.fileno()}]> disconnected. \n"
        send_to_all(disconnect_msg,client_socket)

if __name__ == '__main__':
    if len(sys.argv) < 2:
        print("Usage: {} <port>".format(sys.argv[0]))
        sys.exit(1)

    server_port = int(sys.argv[1])

    #Set up the listener socket
    listener_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    listener_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    listener_socket.bind(('0.0.0.0', server_port))
    listener_socket.listen(10)
    listener_socket.setblocking(False)

    #Set up the master select list
    master_socket_list = [listener_socket]
    print(f"Server started on port {server_port}...")

    while True:
        #Use select to multiplex between the listener socket and the client sockets
        read_sockets, _, _ = select.select(master_socket_list, [],[])

        for sock in read_sockets:
            if sock == listener_socket:
                #New connection request
                handle_new_connection(listener_socket)
            else:
                #Existing cilent sends data
                handle_client_data(listener_socket)
