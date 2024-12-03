ps -ef | grep $(ls *.jar) | grep -v "grep" | awk '{print $2}' | xargs kill
source ~/.zshrc
sdk use java 8.0.422-zulu
nohup java -Xmx128m -Xms128m -jar ./$(ls *.jar)  > /dev/null 2>&1 &