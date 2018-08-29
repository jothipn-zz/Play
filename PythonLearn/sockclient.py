import socket

def doMain():
    serverHost = socket.gethostname()
    serverPort = 8888

    sock = socket.socket()
    sock.connect((serverHost, serverPort))

    data = "test"

    sock.send(data.encode())

    data = sock.recv(1024).decode()

    print("Server Time is " + data)

    sock.close()

if __name__ == '__main__':
    doMain()

