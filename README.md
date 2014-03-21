Apps
====
1. If project is not forked previously fork the project to you account by login into github.com
2. Clone the repository using following command
Ex: git clone git@github.com:hillsreddy/iEagle.git
3. Change the directory to cloned folder
Ex: cd iEagle
4. Get remote data from main repoistory
Ex: git remote add iEagle git@github.com:YanaGit/iEagle.git
5. Fetch the latest data from main repository
Ex: git fetch iEagle
6. Check the branch where you are currently.8686863457
Ex: git branch -v
7. If you are in master branch or other branches that you are not needed then checkout the branch you want
Ex: git checkout Migration-2.9
8. Work on all the changes you have to done in the selected branch
9. Check what are the files you changed by using status command.
Ex: git status
10. Add all the files using add command to your local copy
Ex: git add path
11. Before commit files take update whether any merge conflicts you may get by using pull command
Ex: git pull iEagle Migration-2.9(branchName)
12. Commit all the files you added using add command by using commit command
Ex: git commit -m "comments"
13. Push all the files you committed into your local copy using push command
Ex: git push origin Migration-2.9(branchName)
14. Check you github.com account and send pull request to admin
15. That's it !!!! It is done from your end.If Admin gets any merge conflicts he will update you.
