## jwatch-tutorial
Examples to teach how to use [JWatch](https://github.com/RobertoMessaBrasil/jwatch) - Observer Pattern based framework to control events in java code 

## Steps to use JWatch

### Events, Event Codes, Listeners

1. [Create the event](src/main/java/com/jwatch/tutorial/entity/user/event/UserValidationEvent.java) to characterize what you want to handle
2. [Create enums](src/main/java/com/jwatch/tutorial/entity/user/event/UserValidationCode.java) with to provide the event with data to be used in listener's code
3. [Create one or more listeners](src/main/java/com/jwatch/tutorial/listener/ValidationListener.java) to handle the events

### Publishing events

[Handle the situation](src/main/java/com/jwatch/tutorial/entity/user/UserEntity.java) in your code:

1. Check the situation
2. Instantiate the proper event
3. Populate the event with the necessary data
4. Notify the observer

JWatch EventObserver will loop through all listeners and pass the event for them: the listeners do the rest.

### Using the observer

1. Instanciate the listeners and add the events of interest to it
2. Subscribe the listeners to the observer
3. Handle the InterruptException

## Examples

### Validation

Instead of using exceptions to handle errors, you can create a [validation listener](src/main/java/com/jwatch/tutorial/listener/ValidationListener.java) and decide what to do in your listener.

If the validation is severe and the flow must be interrupted, you can return true from your listener's handleEvent and return true which will throw and InterruptException to be handled by your [main code](src/main/java/com/jwatch/tutorial/validation/ValidationApp.java).

### Security

Your can create a listener to handle security issues in you code.

Let's us say only users with a proper role can create resources.

If a user [without the proper role](src/main/java/com/jwatch/tutorial/entity/user/UserRoleEnum.java) tries to do something forbidden, you [can prevent](src/main/java/com/jwatch/tutorial/listener/SecurityListener.java) it in your listener.

### Log

Specialized [log listeners](src/main/java/com/jwatch/tutorial/listener/LogListener.java) and [events](src/main/java/com/jwatch/tutorial/log/LogEvent.java) can be created to [handle logging](src/main/java/com/jwatch/tutorial/log/LogApp.java) in you code.

Validations, security, etc... can be logged in console, file, queues, observer systems to get you fine grained feedback from your code.

## Comming soon...

Listeners for messaging, timers and much much more! :D

### Let's talk!

Follow my github to get notified.

[Subscribe to my YouTube channel](https://www.youtube.com/@backendjava) because I'll talk about JWatch there

[Let's connect](https://www.linkedin.com/in/robertomessabrasil/) on Linkedin! I'm always open for job proposals ;)


