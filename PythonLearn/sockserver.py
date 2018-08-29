import socket
import datetime


def doMain():
    host = socket.gethostname()
    port = 8888

    sock = socket.socket()

    sock.bind((host, port))

    sock.listen()

    conn, address = sock.accept()

    print("Request From: " + str(address))

    data = str(datetime.datetime.now())
    conn.send(data.encode())

    conn.close()

if __name__ == '__main__':
    doMain()
