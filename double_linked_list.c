#include <stdio.h>
#include <stdlib.h>
//dobule linked list. 

typedef struct Node{
    int data;
    struct Node *next;
    struct Node *prev;
}Node;

//function list
void add_First(Node **head_p, Node **tail_p, int key);
int isEmpty(Node **head_p, Node **tail_p);
Node* create_Node(int key);
void add_Last(Node **head_p, Node **tail_p, int key);
Node* search_node(Node **head_p, int key);
int delete_Node(Node **head_p, int key);
void print(Node **head_p);
void menu(Node **head_p, Node **tail_p, int menu);

int main() {
	Node *head = NULL;
	Node *tail = NULL;
	int menu_num = 0;
	while(1) {
		printf("================\n");
		printf("1. addFirst     \n");
		printf("2. addLast       \n");
		printf("3. delete       \n");
		printf("4. print       \n");
		printf("================\n");		
		scanf("%d",&menu_num);
		menu(&head,&tail,menu_num);
	}
	return 0;
}
void menu(Node **head_p, Node **tail_p,int menu_num) {

	int data = 0;

	switch(menu_num) {
		case 1: {
			//flush enter
			//fflush(stdin);
			printf("Input addFirst data : ");
			scanf("%d" ,&data);
			add_First(head_p,tail_p,data);
			break;
		}
		case 2: {
			fflush(stdin);
			printf("Input addLast data : ");
			scanf("%d" ,&data);
			add_Last(head_p,tail_p,data);
			break;
		}
		case 3: {
			fflush(stdin);
			scanf("Delete data : %d \n" ,&data);
			delete_Node(head_p,data);
		}
		case 4: {
			fflush(stdin);
			print(head_p);
		}
		case 0: {
			return;
		}
	}
	printf("\n");
}
void add_First(Node **head_p, Node **tail_p, int key) {
	Node *head = *head_p;
	Node *new_Node = create_Node(key);

	if(isEmpty(head_p,tail_p)==1) {
		//처음엔 head 와 tail은 모두 newNode를 가리킨다.
    	*head_p = new_Node;
    	*tail_p = new_Node;
    	(*head_p)->next = (*tail_p);
    	(*head_p)->prev = NULL;
     	(*tail_p)->prev = (*head_p);
    	(*tail_p)->next = NULL;   	
    	return;	
	}
	new_Node->next = head;
	head->prev = new_Node;


	(*head_p) = new_Node;
}

int isEmpty(Node **head_p, Node **tail_p) {
	//head, tail 이 모두 null일 경우 1을 return
	return ((*head_p)==NULL && (*tail_p)==NULL) ? 1 : 0;
}
Node* create_Node(int key) {
	//allocation process.
	Node *new_Node  = (Node *)malloc(sizeof(Node));
	new_Node->next = NULL;
	new_Node->prev = NULL;
	new_Node->data = key;

	return new_Node;
}

void add_Last(Node **head_p, Node **tail_p, int key) {
	Node *tail = *tail_p;
	Node *new_Node  = create_Node(key);

	if(isEmpty(head_p,tail_p)==1) {
		//처음엔 head 와 tail은 모두 newNode를 가리킨다.
    	*head_p = new_Node;
    	*tail_p = new_Node;
    	(*head_p)->next = (*tail_p);
    	(*head_p)->prev = NULL;
     	(*tail_p)->prev = (*head_p);
    	(*tail_p)->next = NULL;   	
    	return;	
	}
	new_Node->prev = tail;
	tail->next = new_Node;
	
	new_Node->next = NULL;
	(*tail_p) = new_Node;

}
//내가 원하는 데이터 값의 노드 탐색 find first 방식
Node* search_node(Node **head_p, int key) {
	Node *pos = *head_p;
	Node *tmp = NULL;
	//찾아야 하는 데이터가 헤더에 있는 경우 바로 헤더의 주소 리턴.
	if((*head_p)->data == key) {
		if((*head_p)->next != NULL) {
			(*head_p) = (*head_p)->next;
		}else {
			(*head_p) = NULL;
		}
		//free(pos);

		return pos;
	}
	//pos는 head 다음 값으로 지정.
	pos = pos -> next;
	while(pos->next != NULL) {
		if(pos->data == key) {
			tmp = pos;
			printf("delete data : %d\n",tmp->data);
			pos->prev->next = pos->next;
			pos->next->prev = pos->prev;
			//free(pos);

			return pos;
		}
		pos = pos->next;
	}
	//만약 찾지 못했다면 return -1;
	printf("Can not find Data you want to delete\n");
	return -1;
}
int delete_Node(Node **head_p, int key) {
	Node *pos = search_node(head_p,key);
	
	if(pos != -1) {
		free(pos);
		return 0;
	}
	//찾지 못했을 경우 -1리턴
	return -1;
}

void print(Node **head_p) {
	Node *pos = *head_p;
	while(pos->next != NULL) {
		printf("pos -> data : %d \n",pos->data);
		pos = pos->next;
	}
	printf("pos -> data : %d \n",pos->data);
}
