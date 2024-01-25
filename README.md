

# account-manage

### Stack Tecnológico 
* Java 17
* SpringBoot 3.1.5
* Maven 3.9.4


## Configuración del ambiente Desarrollo

### 1. Configuraciones


### 2. Documentación de la API (Swagger)

La especificación del servicio a través de la siguiente ruta:[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


## Integración Contínua

En el proyecto estan presentes los archivos wrapper de Maven (directorio **.mvn**), esto ayudará a que no necesitemos una imagen específica de Maven o descargar Maven adicionalmente durante la ejecución del pipeline. 

Además de los archivos wrapper de Maven, existe la carpeta **.m2** dentro de este mismo proyecto donde esta la configuración de maven para ejecutar el pipeline en sus diferentes etapas.

## Trazabilidad

Se utiliza el agente de [OpenTelemetry](https://opentelemetry.io/docs/instrumentation/java/getting-started/#instrumentation) para que pueda funcionar adecuadamente.

El agente esta disponible en linea: [opentelemetry-javaagent.jar](https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar)

### 1. Iniciar la aplicación


___


### Contenedores

--
Para construir el contenedor, se debe construir la imagen en Docker.



A continuación se muestra como construir la imagen simulando todos los pasos del pipeline, utilizando el nombre raíz del proyecto como nombre de la imagen.

```bash
docker build -f Development.dockerfile -t account-manage --network host .
```

--
Para levantar el contenedor

```bash
docker run -it --rm --name account-manage -p 8080:8080 -d account-manage --network=host
```

Agregar `-d` para hacerlo detachable. 

--
Para comprobar que el contenedor levanto correctamente

```bash
docker ps -a
```

Esto listara los contenedores corriendo en el equipo, si todo esta bien debería poder acceder al contenedor desde su 
navegador con [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

-- 
Ver logs del contenedor:
```shell
docker logs account-manage
```

--
Para acceder a través de TTY al contenedor:
```shell
docker exec -it account-manage /bin/sh
```

