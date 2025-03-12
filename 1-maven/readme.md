docker run --name mi-postgres -e POSTGRES_PASSWORD=postgres -p 15432:5432 -d postgres
docker exec -it mi-postgres psql -U postgres    