# APP NISUM 
App prueba técnica Nisum

## Prerrequisitos

   <ul>
     <li>Java 17</li>
     <li>Puerto 8081 libre</li>
   </ul>

## Conexiones

<p>Datos de conexión a la base de datos H2</p>

| PROPIEDAD | VALOR                       |
|-----------|-----------------------------|
| JDBC URL  | jdbc:h2:mem:pruebatecnicadb |
| User Name | sa                          |
| Password  | admin                       |

## Instalación

### Desplegado
<p>Ingrese a la app, e inicie a probar el servicio a través de:</p>

   <ul>
     <li><a href="https://site--back--8kyzyfq4vw5r.code.run/nisum/swagger-ui/index.html">online-app-swagger-ui</a></li>
     <li><a href="https://site--back--8kyzyfq4vw5r.code.run/nisum/db/">online-db-h2</a></li>
   </ul>

### Local
<p>

</p>

1. Clone el repo
   ```console
   git clone https://github.com/sejuapp/prueba-nisum-ms.git
   ```
2. Importe como un proyecto de Maven con su IDE favorito (STS, IntelliJ IDEA), espere que las dependencias se instalen.

3. En la raiz del proyecto abra la consola y ejecute el comando.
   ```bash
   mvn clean verify
   ```
4. Corra el proyecto e ingrese a 
 
   <ul>
     <li><a href="http://localhost:8081/nisum/swagger-ui/index.html#">local-app-swagger-ui</a></li>
     <li><a href="http://localhost:8081/nisum/db/">local-db-h2</a></li>
   </ul>


## Observaciones
   <p>
      Los .sql se encuentran en la carpeta 'resources' y son schema.sql y
      data.sql. Estos se ejecutan al momento de correr la aplicación y crean tanto las tablas como el registro por defecto para validar el email.
   </p>

