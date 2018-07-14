# Git Flow
이슈 단위로 브랜치를 만들고, PR 후에는 폐기.  
> 그 동안은 그냥 폐기 없이 써왔던 듯... 
> default branch를 다시 셋팅하고, 기능 단위로 branch 나눠서 관리 해보기. 


## Branch 만들기. 
새로운 이슈가 뜨면, 해당 number 확인.  
ceckout해서 다른 브랜치로 가는 것. 어디에서 나가느냐가 중요하다.  
`devlop` 에서 체크아웃 해서 `feature/num` 형태로 만듦. 

예시 ) git checkout feature/2-edit_readme  

commit message 작성법 : #(num) README.md 수정  

feature를 합쳐서 devlop으로,,,,,   