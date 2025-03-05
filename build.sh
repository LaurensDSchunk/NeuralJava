javac -d build $(find src -name "*.java")

cd build
java Main
cd ..
