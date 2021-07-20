package TypingRacer;

class LinkedList {

    Node head;
    Node pointer;
    Node tail;

    void addAtTail(String n) {
        Node newNode = new Node();
        newNode.word = n;
        Node temp = head;
        tail = head;
        //check if the current LL is empty
        if(isEmpty() == true){
            if(n != null){
                head = newNode;
                pointer = head;
                tail=head;
            }
        }else {
            //loop through all elements to find tail
            while (temp != null) {
                if (temp.next == null) {
                    temp.next = newNode;
                    newNode.next = null;
                    tail = newNode;
                    break;
                } else {
                    temp = temp.next;
                }
                pointer = head;
            }
        }
    }


    boolean isEmpty() {
        return head == null;
    }

    int getCount()
    {
        Node temp = head;
        int count = 0;
        //loop and increment counter
        while (temp != null)
        {
            count++;
            temp = temp.next;
        }
        return count;
    }

    void print() {
        Node temp = head;
        while (temp != null) { //if it's not null then
            System.out.print(temp.word + "->");//print it
            temp = temp.next;//set temp to the next node
        }
    }

}

 class Node {
    String word;
    Node next;
 }



