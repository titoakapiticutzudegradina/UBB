//#include <cstddef>
#include <cstddef>
#define _WINSOCK_DEPRECATED_NO_WARNINGS 1
#include<stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>
#include <stdio.h>
#include <string>
#define MAX 250

#define closesocket close
typedef int SOCKET;

//void nrspaces(SOCKET c, int length){
//    int count;
//    char a;
//    std::string buffer;
//    count = 0;
//    for (int i = 0; i < length; i++){
//        recv(c, &a, sizeof(char), 0);
//        buffer += a;
//    }
//    for (int i = 0; i < length; i++){
//        if(buffer[i] == '_') count ++;
//    }
//    count = htonl(count);
//    send(c, (char*)&count, sizeof(count), 0);
//}

int main(){
    SOCKET s;
    struct sockaddr_in server, client;
    int c, l;

    s = socket(AF_INET, SOCK_STREAM, 0);
    if (s < 0){
        printf("Error! Creation server socket\n");
        return 1;
    }

    memset(&server, 0, sizeof(server));
    server.sin_port = htons(1234);
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;

    if(bind(s, (struct sockaddr*)&server, sizeof(server)) < 0){
        printf("Error! Binding\n");
        return 1;
    }

    listen(s, 5);

    l = sizeof(client);
    memset(&client, 0, sizeof(client));

    while(1){
        printf("Listening for incomming connections\n");
        c = accept(s, (struct sockaddr*)&client, (socklen_t*)&l);

        if(c < 0){
            printf("Error! Accepting connection\n");
            continue;
        }

        printf("Incomming connected client from: %s:%d\n", inet_ntoa(client.sin_addr), ntohs(client.sin_port));
        ////here is a problem how the fuck do you recive a string ...
        //int res,length;
        //res = recv(c, (char*)&length, sizeof(length), 0);
        //if(res != sizeof(length)) printf("Error! Receving operand");
        //length = ntohl(length);
        //nrspaces(c, length);

        //receving the length of the string
        int length, res;
        res = recv(c, (char*)&length, sizeof(length), 0);
        if(res < 0){
            printf("Error! Receving data(length)");
            closesocket(c);
            continue;
        }
        length = ntohs(length);
        printf("the length recevied: %d\n", length);

        //receive string
        char buff;
        std::string str;
        for(int i = 0; i < length; i++){
            res = recv(c, &buff, sizeof(buff), 0);
            if(res < 0){
                printf("Error! Receving data(string)");
                closesocket(c);
                continue;
            }
            str += buff;
        }

        //count the number of spaces
        int count = 0;
        for (size_t i = 0; i < str.size(); i++){
            if(str[i] == '_')
                count++ ;
        }

        //send the data to the client
        count = ntohs(count);
        send(c, (char* )&count, sizeof(count), 0);
        closesocket(c);
    }
}
