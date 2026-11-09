# QRScanner

Android Studio ve Java ile geliştirilmiş, QR kod okuyup içeriğini AES ile çözmeye yönelik bir Android uygulaması.

> **Not:** Uygulama QR kod oluşturmaz veya şifreleme yapmaz; yalnızca taranan QR kodun içeriğini çözmeyi dener.

## Özellikler

- ZXing tabanlı kamera ile QR kod tarama
- Tarama sonucundaki Base64 kodlu içeriği AES ile çözme
- Çözülen metni uygulama içinde gösterme

## Gereksinimler

- Android Studio
- Android SDK 33
- Android 5.0 (API 21) veya üzeri bir cihaz ya da emülatör
- Kamera erişimi (gerçek cihazda QR taramak için)

## Kurulum ve çalıştırma

1. Depoyu klonlayın:

   ```bash
   git clone https://github.com/melihtop35/QRScanner.git
   ```

2. Projeyi Android Studio'da açın ve Gradle senkronizasyonunun tamamlanmasını bekleyin.
3. Bir Android cihazı veya emülatör seçip uygulamayı çalıştırın.
4. Kamera izni istendiğinde izin verin ve **Scan** düğmesine basarak QR kodu tarayın.

## QR kod biçimi ve şifre çözme

Uygulama, taranan metni `MainActivity.java` içindeki `decryptAES` metodu ile çözmeye çalışır. Mevcut uygulama koduna göre QR içeriği, noktalı virgülle (`;`) ayrılmış bir veya daha fazla Base64 kodlu AES şifreli parçadan oluşmalıdır. Şifreleme anahtarı şu anda kaynak kodda sabit olarak tanımlanmıştır:

```java
"1234567890123456"
```

Kendi QR kodlarınızla uyum için QR içeriğinin, şifreleme yönteminin ve anahtarın uygulamadaki çözme mantığıyla eşleşmesi gerekir. Anahtarı değiştirmek için `MainActivity.java` dosyasındaki `decryptAES` çağrısını düzenleyin.

> **Güvenlik uyarısı:** Bu anahtar örnek amaçlıdır ve kaynak kodda sabittir. Gerçek veya hassas verileri korumak için bu örnek anahtarı kullanmayın. Üretim uygulamalarında anahtar yönetimi, kullanılan AES modu ve bütünlük doğrulaması güvenli biçimde tasarlanmalıdır.

## Kullanılan teknolojiler

- Java
- Android Gradle Plugin 8.0.2
- AndroidX AppCompat
- Material Components
- ZXing Android Embedded 4.3.0

## Lisans

Bu depoda bir lisans dosyası bulunmuyor. Yeniden kullanım ve dağıtım koşulları için depo sahibine danışın.
