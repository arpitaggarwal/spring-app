1. Replace the `nginx.conf`

2. Put the content of the [static][1] folder at `/Users/ArpitAggarwal/product/spring-app`, else change the [location of static content][2] inside `nginx.conf`

3. Start Nginx on port `80` and Tomcat at `8080`

4. Access the application at: `http://localhost/spring-app/welcome/user?lang=de&theme=blue`

[1]: https://github.com/arpitaggarwal/spring-app/tree/master/static/
[2]: https://github.com/arpitaggarwal/spring-app/blob/master/nginx.conf#L49
