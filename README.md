# EbloMessenger

---
Этот мессенджер создан исключительно для само-практики и постироничных целей, не относитесь к этому серьезно

---
## API
Создание и отправка сообщений в чат:

```java
MessageBuilder.newBuilder()
    .user(0)
    .chat(1)
    
    .text("Санек, а ты знал, что ебломессенджер накодил стонлекс?")
    
    .edited(true)
    .deleted(false)
    
    .attachment(AttachmentFactory.createAttachment(0, ...))
    .send();
```
---

* in development ...