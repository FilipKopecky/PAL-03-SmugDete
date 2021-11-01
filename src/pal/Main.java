package pal;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(
                    new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }


        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            return ret;
        }


        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                    BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }

    public static void main(String[] args) throws IOException {
        // write your code here
        Reader reader = new Reader("datasets/pub10.in");
        int numNodes = reader.nextInt();
        int numEdges = reader.nextInt();
        int numNodesPack = reader.nextInt();
        int numEdgesPack = reader.nextInt();

        Node[] graph = new Node[numNodes];
        for (int i = 0; i < numNodes; i++) {
            graph[i] = new Node(i);
        }

        for (int i = 0; i < numEdges; i++) {
            int source = reader.nextInt();
            int destination = reader.nextInt();
            graph[source].neighbours.add(graph[destination]);
            graph[destination].neighbours.add(graph[source]);
        }

        Alg alg = new Alg(graph,numNodesPack,numEdgesPack);
        alg.calculateSub();
       // alg.getIsomorphisms();
        alg.getIsomorphismsPacks();



    }
}
