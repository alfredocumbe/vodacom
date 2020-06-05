# Vodacom System Backend Development Challenge

Steps to run this project:

1. Start your Docker daemon
2. Execute `./buildAndRun.sh` (Linux/MacOs) or `buildAndRun.bat` (Windows)
3. Wait until Open Liberty is up- and running (`docker logs -f challenge`)
4. Base URI http://localhost:9080/challenge/
        Operations
   		GET:  http://localhost:9080/challenge/history
		POST: http://localhost:9080/challenge/random
		GET:  http://localhost:9080/challenge/stats
		GET:  http://localhost:9080/challenge/pending
		POST: http://localhost:9080/challenge/poolSize/threads
		PUT:  http://localhost:9080/challenge/requestid/cancel
		
   `Apliacation Infrastructure` 
	Open Liberty: Framework for building loud-native Java microservices.
	https://openliberty.io/

