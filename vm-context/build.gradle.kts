subprojects{
    apply{
        plugin("java-library")
    }
    dependencies{
        compileOnly("jakarta.enterprise:jakarta.enterprise.cdi-api:4.0.1")
    }
}