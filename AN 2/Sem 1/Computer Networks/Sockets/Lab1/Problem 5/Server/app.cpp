#define _WINSOCK_DEPRECATED_NO_WARNINGS 1
// exists on all platforms
#include <stdio.h>
// this section will only be compiled on NON Windows platforms
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>

#define closesocket close
typedef int SOCKET;

void send_divisors(SOCKET c, uint16_t number){
    uint16_t divisors[100];
    int count = 0;
    for (uint16_t i = 1; i <= number; i++){
        if(number % i == 0){
            count++;
            divisors[count] = i;
        }
    }

    //Send the number of the divisors
    uint16_t nrdiv = htons(count);
    send(c, &nrdiv, sizeof(nrdiv),0);

    //send the divisors
    for (int i=1; i<=count; i++){
        uint16_t div = htons(divisors[i]);
        printf("%d\n", divisors[i]);
        send(c, &div, sizeof(div),0);
    }
}

int main(){
    SOCKET s;
    struct sockaddr_in server, client;
    int c, l;

    // create the server socket
    s = socket(AF_INET, SOCK_STREAM, 0);
    if(s < 0){
        printf("Error! Creating server socket.\n");
        return 1;
    }

    // set the server address
    memset(&server,0,sizeof(server));
    server.sin_port = htons(1234);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;

    // bind the socket to our specified IP and port
    if(bind(s, (struct sockaddr*)&server, sizeof(server)) < 0){
        perror("Error! Bind");
        return 1;
    }

    listen(s,5);

    l = sizeof(client);
    memset(&client, 0, sizeof(client));

    while(1){
        uint16_t a;
        printf("Listening for incomming connection\n");
        c = accept(s, (struct sockaddr*)&client, (socklen_t*)&l);
        if(c < 0){
            printf("Error! Accept");
            continue;
        }
        printf("Incomming connected client from: %s:%d", inet_ntoa(client.sin_addr), ntohs(client.sin_port));
        //serving the connected client
        int res = recv(c,(char*)&a, sizeof(a),0);
        //check we got an unsigened short value
        if(res != sizeof(a)) printf("Error! Receving operand\n");


        //decode the received number
        a = ntohs(a);
        send_divisors(c, a);
        closesocket(c);
    }
}
