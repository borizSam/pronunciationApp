# Invalidate Cache

> When switching branches in IntelliJ IDEA for Spring Boot projects a common issue appear: **no class is load into the IDE **

Here are some steps to resolve the problem:

1. Invalidate caches and restart: Go to File > Invalidate Caches / Restart[5](https://stackoverflow.com/questions/12132003/getting-cannot-find-symbol-in-java-project-in-intellij).
2. Rebuild the project: Select Build > Rebuild Project[5](https://stackoverflow.com/questions/12132003/getting-cannot-find-symbol-in-java-project-in-intellij).
3. Reimport Maven dependencies: Right-click on the project, select Maven > Reimport[5](https://stackoverflow.com/questions/12132003/getting-cannot-find-symbol-in-java-project-in-intellij).
4. Enable annotation processing: Go to Preferences > Build, Execution, Deployment > Compiler > Annotation Processors and check "Enable annotation processing"[1](https://stackoverflow.com/questions/53031917/logback-references-are-red-in-intellij-using-spring-boot/53036773).
5. Ensure the Lombok plugin is installed: Go to Preferences > Plugins, search for Lombok, and install it if not already present[1](https://stackoverflow.com/questions/53031917/logback-references-are-red-in-intellij-using-spring-boot/53036773).
6. Check Java version: Make sure IntelliJ is set to use the correct Java version for your project[2](https://stackoverflow.com/questions/11632120/why-so-red-intellij-seems-to-think-every-declaration-method-cannot-be-found-res).
7. Verify source folders: Ensure that your source folders are correctly marked as "Sources" in the Project Structure (File > Project Structure > Modules > Sources)[4](https://www.jmri.org/help/en/html/doc/Technical/IntelliJ.shtml).
8. Delete the .idea folder: If the issue persists, try deleting the .idea folder and reimporting the project[2](https://stackoverflow.com/questions/11632120/why-so-red-intellij-seems-to-think-every-declaration-method-cannot-be-found-res).
