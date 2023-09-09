# Proiect BD
# Install Java 19
sudo apt install openjdk-19-jdk

# Install PostgreSQL
sudo apt install postgresql postgresql-contrib

# Start PostgreSQL
sudo service postgresql start

# Create cinema database
sudo -u postgres createdb cinema

# Compile the project
mvn clean install

# Run the application
java -jar target/cinema.jar
