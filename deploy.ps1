Set-Location "IMS-ImageApp"
mvn clean package
Set-Location ".."
docker-compose up --build