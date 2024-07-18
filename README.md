## jwatch-tutorial
Examples to teach how to use [JWatch](https://github.com/RobertoMessaBrasil/jwatch) - Observer Pattern based framework to control events in java code 

## Steps to use JWatch

### Events, Event Codes, Listeners

1. [Create the event](src/main/java/com/jwatch/tutorial/entity/user/event/UserValidationEvent.java) to characterize what you want to handle
2. Create enums with to provide the event with data to be used in listener's code
3. Create one or more listeners to handle the events

### Using the observer

1. Instanciate the listeners and add the events of interest to it
2. Subscribe the listeners to the observer
3. Handle the InterruptException

## Examples

