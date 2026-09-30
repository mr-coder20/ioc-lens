<div align="center">

# 🔍 IOC Lens

**Copy it. Know in a second.**

Instant, privacy-first threat-indicator triage for SOC analysts and defenders.

[![Release](https://img.shields.io/github/v/release/mr-coder20/ioc-lens?include_prereleases&label=release&color=2ea44f)](https://github.com/mr-coder20/ioc-lens/releases/latest)
[![Downloads](https://img.shields.io/github/downloads/mr-coder20/ioc-lens/total?color=blue)](https://github.com/mr-coder20/ioc-lens/releases)
[![Stars](https://img.shields.io/github/stars/mr-coder20/ioc-lens?style=flat&color=yellow)](https://github.com/mr-coder20/ioc-lens/stargazers)
[![CI](https://github.com/mr-coder20/ioc-lens/actions/workflows/ci.yml/badge.svg)](https://github.com/mr-coder20/ioc-lens/actions)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)
[![Kotlin](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

### [⬇️ Download from Releases](https://github.com/mr-coder20/ioc-lens/releases)

**🪟 Windows &nbsp;·&nbsp; 🍎 macOS &nbsp;·&nbsp; 🐧 Linux**

<br>

[**English**](#english) &nbsp;·&nbsp; [**فارسی**](#persian) &nbsp;·&nbsp; [**Русский**](#russian)

<!-- Add a 10-second demo GIF as docs/assets/demo.gif, then uncomment:
<br><br>
<img src="docs/assets/demo.gif" alt="IOC Lens demo" width="760">
-->

</div>

---

<a id="english"></a>

# 🇬🇧 English

<div align="center">

[English](#english) · [فارسی](#persian) · [Русский](#russian)

**📋 Copy** &nbsp;→&nbsp; **🛡️ Privacy Guard** &nbsp;→&nbsp; **🌐 Threat-intel sources** &nbsp;→&nbsp; **🎯 One clear verdict**

</div>

**IOC Lens** is a lightweight, privacy-first desktop tool for SOC analysts and defenders. Copy an IP address, domain, URL or file hash, and get one clear, explainable verdict from several threat-intelligence sources at once, without juggling browser tabs.

> 🚧 IOC Lens is under active development. Features and capabilities will grow and change over time.

## 💡 Why IOC Lens

Checking a suspicious indicator usually means copying it, opening several websites, pasting it again and again, and comparing different answers by hand. IOC Lens does all of that in one step.

| Without IOC Lens | With IOC Lens |
|---|---|
| Several tabs, several logins | One quick lookup |
| Different answers to compare by hand | One combined, explained verdict |
| Risk of pasting internal data into outside sites | Internal indicators stay on your machine |
| Heavy platforms for a quick check | A small, fast desktop app |

## ✨ Highlights

- ⚡ **Instant**: check what you just copied in about a second
- 🌐 **Many sources, one answer**: results from several free threat-intelligence services, combined and explained
- 🔎 **Explainable**: every verdict shows which sources contributed and why
- 🛡️ **Privacy-first**: internal indicators never leave your machine
- 🔑 **Your keys, your control**: no account, no telemetry, keys stay local
- 🪶 **Light and native**: small, fast, for **Windows, macOS and Linux**
- 🔓 **Open source** under the MIT license

## 📥 Install

1. Open the **[Releases page](https://github.com/mr-coder20/ioc-lens/releases)**.
2. Under **Assets** of the latest release, download the file for your system:

   | System | File |
   |---|---|
   | 🪟 Windows | `.msi` |
   | 🍎 macOS | `.dmg` |
   | 🐧 Linux | `.deb` |

3. Install it:

<details>
<summary><b>🪟 Windows</b></summary>

1. Run the downloaded `.msi` installer and follow the wizard.
2. If **SmartScreen** shows a warning (early builds may be unsigned), choose **More info → Run anyway**.
3. Start **IOC Lens** from the Start menu. It lives in the system tray.

</details>

<details>
<summary><b>🍎 macOS</b></summary>

1. Open the `.dmg` and drag **IOC Lens** into **Applications**.
2. On first launch, **right-click the app → Open → Open** if macOS says it cannot verify the developer.
3. Allow the permissions macOS asks for (for example, for the global shortcut).

</details>

<details>
<summary><b>🐧 Linux</b></summary>

1. Install the package:
   ```bash
   sudo apt install ./<downloaded-file>.deb
   ```
2. Launch **IOC Lens** from your app menu.
3. On GNOME, install the *AppIndicator* extension if you do not see the tray icon.

</details>

> 🔐 **Recommended:** compare the SHA-256 of your download with `SHA256SUMS.txt` from the same release.

## 🚀 Get started

1. Open **Settings → Providers** and add a **free API key** for at least one source (for example VirusTotal, AbuseIPDB, AlienVault OTX or abuse.ch URLhaus). Each service offers free keys on its website.
2. Copy any indicator: an IP, domain, URL or file hash.
3. Use the shortcut shown in Settings, or click the tray icon. Your verdict appears in a moment.

## 🎯 Understanding verdicts

| Verdict | Meaning |
|---|---|
| 🟢 **Clean** | No meaningful detections |
| 🟡 **Low risk** | Weak or old signals; review in context |
| 🟠 **Suspicious** | Notable detections; worth investigating |
| 🔴 **Malicious** | Strong, corroborated detections |
| ⚪ **No data** | Not known to your enabled sources. This is **not** the same as safe |

A verdict is a triage aid, not a final judgment. Always apply your own analysis.

## 🛡️ Privacy & security

- **Privacy Guard** blocks private and internal indicators (such as internal IP addresses and internal domain names) before anything is sent out. You can add your own rules.
- Only the indicator itself is sent, only to the sources you enabled, over HTTPS.
- **No backend, no account, no telemetry.**
- API keys are kept in your operating system's secure storage.
- Found a vulnerability? Please read [SECURITY.md](SECURITY.md).

## ❓ FAQ

<details>
<summary><b>Is IOC Lens free?</b></summary>

Yes. It is open source under the MIT license. The threat-intelligence services it queries offer free API keys; their own terms and limits apply.
</details>

<details>
<summary><b>Does it send my data to you?</b></summary>

No. There is no IOC Lens server. Lookups go directly from your computer to the sources you enabled.
</details>

<details>
<summary><b>Why "No data" instead of "Clean"?</b></summary>

Not being in a database does not make something safe. New infrastructure is often unknown, so IOC Lens never presents a missing result as a clean one.
</details>

<details>
<summary><b>The shortcut does not work.</b></summary>

Another app may be using the same shortcut. Change it in Settings, or use the tray icon instead.
</details>

## 🤝 Contribute

Ideas, bug reports, new sources, translations and code are all welcome. Read [CONTRIBUTING.md](CONTRIBUTING.md), open an [issue](https://github.com/mr-coder20/ioc-lens/issues), or send a pull request.

**⭐ If IOC Lens saves you time, a star helps other defenders find it.**

Released under the [MIT License](LICENSE).

<div align="right"><a href="#english">↑ Back to top</a></div>

---

<a id="persian"></a>

# 🇮🇷 فارسی

<div dir="rtl">

<div align="center">

[English](#english) · [فارسی](#persian) · [Русский](#russian)

**📋 کپی** &nbsp;←&nbsp; **🛡️ Privacy Guard** &nbsp;←&nbsp; **🌐 منابع Threat Intel** &nbsp;←&nbsp; **🎯 یک حکم روشن**

### کپی کن، در یک ثانیه بفهم.

</div>

**IOC Lens** یک ابزار دسکتاپ سبک و حریم‌خصوصی‌محور برای تحلیلگران SOC و مدافعان امنیتی است. یک آدرس IP، دامنه، URL یا هش فایل را کپی کنید و بدون باز کردن چندین تب مرورگر، یک حکم روشن و قابل‌توضیح از چند منبع threat intelligence بگیرید.

> 🚧 IOC Lens در حال توسعه‌ی فعال است. ویژگی‌ها و قابلیت‌ها با گذر زمان گسترش پیدا می‌کنند و ممکن است تغییر کنند.

## 💡 چرا IOC Lens؟

بررسی یک نشانگر مشکوک معمولاً یعنی کپی کردن، باز کردن چند وب‌سایت، چندبار paste کردن و مقایسه‌ی دستی پاسخ‌های مختلف. IOC Lens همه‌ی این کارها را در یک مرحله انجام می‌دهد.

| بدون IOC Lens | با IOC Lens |
|---|---|
| چند تب، چند ورود به سایت | یک جست‌وجوی سریع |
| پاسخ‌های متفاوت برای مقایسه‌ی دستی | یک حکم ترکیبی و توضیح‌داده‌شده |
| خطر paste کردن اطلاعات داخلی در سایت‌های بیرونی | نشانگرهای داخلی روی دستگاه شما می‌مانند |
| پلتفرم‌های سنگین برای یک بررسی سریع | یک برنامه‌ی دسکتاپ کوچک و سریع |

## ✨ ویژگی‌های کلی

- ⚡ **فوری**: نتیجه‌ی آنچه تازه کپی کرده‌اید در حدود یک ثانیه
- 🌐 **چند منبع، یک پاسخ**: ترکیب نتایج چند سرویس رایگان threat intelligence همراه با توضیح
- 🔎 **قابل‌توضیح**: هر حکم نشان می‌دهد کدام منابع و چرا در آن نقش داشته‌اند
- 🛡️ **حریم خصوصی اول**: نشانگرهای داخلی هرگز از دستگاه شما خارج نمی‌شوند
- 🔑 **کلیدها دست خودتان است**: بدون حساب کاربری، بدون تله‌متری، کلیدها فقط محلی
- 🪶 **سبک و بومی**: کوچک و سریع، برای **ویندوز، مک و لینوکس**
- 🔓 **متن‌باز** با مجوز MIT

## 📥 نصب

1. **[صفحه‌ی Releases](https://github.com/mr-coder20/ioc-lens/releases)** را باز کنید.
2. در بخش **Assets** آخرین نسخه، فایل مخصوص سیستم خود را دانلود کنید:

   | سیستم | فایل |
   |---|---|
   | 🪟 ویندوز | `.msi` |
   | 🍎 مک | `.dmg` |
   | 🐧 لینوکس | `.deb` |

3. نصب کنید:

<details>
<summary><b>🪟 ویندوز</b></summary>

1. فایل `.msi` دانلودشده را اجرا کنید و مراحل نصب را ادامه دهید.
2. اگر **SmartScreen** هشدار داد (نسخه‌های اولیه ممکن است امضا نداشته باشند)، **More info ← Run anyway** را بزنید.
3. **IOC Lens** را از منوی Start اجرا کنید. برنامه در system tray قرار می‌گیرد.

</details>

<details>
<summary><b>🍎 مک</b></summary>

1. فایل `.dmg` را باز کنید و **IOC Lens** را به **Applications** بکشید.
2. اگر macOS گفت توسعه‌دهنده تأیید نشده، در اولین اجرا **راست‌کلیک روی برنامه ← Open ← Open** را بزنید.
3. مجوزهایی را که macOS می‌خواهد بدهید (مثلاً برای میانبر سراسری).

</details>

<details>
<summary><b>🐧 لینوکس</b></summary>

1. بسته را نصب کنید:
   ```bash
   sudo apt install ./<نام-فایل-دانلودشده>.deb
   ```
2. **IOC Lens** را از منوی برنامه‌ها اجرا کنید.
3. در GNOME اگر آیکون tray را نمی‌بینید، افزونه‌ی *AppIndicator* را نصب کنید.

</details>

> 🔐 **توصیه‌شده:** مقدار SHA-256 فایل دانلودشده را با `SHA256SUMS.txt` همان نسخه مقایسه کنید.

## 🚀 شروع کار

1. به **Settings ← Providers** بروید و برای حداقل یک منبع (مثلاً VirusTotal، AbuseIPDB، AlienVault OTX یا abuse.ch URLhaus) یک **کلید API رایگان** وارد کنید. هر سرویس روی وب‌سایت خودش کلید رایگان می‌دهد.
2. هر نشانگری را کپی کنید: IP، دامنه، URL یا هش فایل.
3. از میانبری که در Settings نوشته شده استفاده کنید یا روی آیکون tray کلیک کنید. حکم شما در چند لحظه نمایش داده می‌شود.

## 🎯 درک حکم‌ها

| حکم | معنی |
|---|---|
| 🟢 **Clean** | تشخیص معناداری وجود ندارد |
| 🟡 **Low risk** | سیگنال ضعیف یا قدیمی؛ در بافت بررسی کنید |
| 🟠 **Suspicious** | تشخیص‌های قابل‌توجه؛ ارزش بررسی دارد |
| 🔴 **Malicious** | تشخیص قوی و تأییدشده توسط چند منبع |
| ⚪ **No data** | برای منابع فعال شما ناشناخته است. این **معادل امن بودن نیست** |

حکم فقط ابزار کمکی تریاژ است، نه قضاوت نهایی. همیشه تحلیل خودتان را هم اعمال کنید.

## 🛡️ حریم خصوصی و امنیت

- **Privacy Guard** نشانگرهای خصوصی و داخلی (مثل IPهای داخلی و نام دامنه‌های داخلی) را قبل از ارسال هر چیزی مسدود می‌کند. می‌توانید قوانین خودتان را هم اضافه کنید.
- فقط خود نشانگر و فقط به منابعی که فعال کرده‌اید، از طریق HTTPS ارسال می‌شود.
- **بدون بک‌اند، بدون حساب کاربری، بدون تله‌متری.**
- کلیدهای API در ذخیره‌ی امن سیستم‌عامل شما نگهداری می‌شوند.
- آسیب‌پذیری پیدا کردید؟ لطفاً [SECURITY.md](SECURITY.md) را بخوانید.

## ❓ پرسش‌های متداول

<details>
<summary><b>IOC Lens رایگان است؟</b></summary>

بله. متن‌باز است و با مجوز MIT منتشر می‌شود. سرویس‌های threat intelligence که از آن‌ها پرس‌وجو می‌شود کلید API رایگان ارائه می‌دهند؛ شرایط و محدودیت‌های خودشان اعمال می‌شود.
</details>

<details>
<summary><b>اطلاعات من برای شما ارسال می‌شود؟</b></summary>

خیر. سروری برای IOC Lens وجود ندارد. جست‌وجوها مستقیماً از کامپیوتر شما به منابعی که فعال کرده‌اید می‌روند.
</details>

<details>
<summary><b>چرا «No data» و نه «Clean»؟</b></summary>

نبودن در یک پایگاه‌داده یعنی امن بودن نیست. زیرساخت‌های جدید اغلب ناشناخته‌اند، پس IOC Lens هرگز نبودِ نتیجه را به‌عنوان «پاک» نشان نمی‌دهد.
</details>

<details>
<summary><b>میانبر کار نمی‌کند.</b></summary>

ممکن است برنامه‌ی دیگری از همان میانبر استفاده کند. آن را در Settings تغییر دهید یا به‌جایش از آیکون tray استفاده کنید.
</details>

## 🤝 مشارکت

ایده، گزارش باگ، منبع جدید، ترجمه و کد همگی خوش‌آمدند. [CONTRIBUTING.md](CONTRIBUTING.md) را بخوانید، یک [issue](https://github.com/mr-coder20/ioc-lens/issues) باز کنید یا pull request بفرستید.

**⭐ اگر IOC Lens وقتتان را گرفته، یک ستاره به پیدا شدنش توسط مدافعان دیگر کمک می‌کند.**

منتشرشده با [مجوز MIT](LICENSE).

<div align="left"><a href="#persian">↑ بازگشت به بالا</a></div>

</div>

---

<a id="russian"></a>

# 🇷🇺 Русский

<div align="center">

[English](#english) · [فارسی](#persian) · [Русский](#russian)

**📋 Копируй** &nbsp;→&nbsp; **🛡️ Privacy Guard** &nbsp;→&nbsp; **🌐 Источники threat intel** &nbsp;→&nbsp; **🎯 Один понятный вердикт**

### Скопируй. Узнай за секунду.

</div>

**IOC Lens** — лёгкое десктопное приложение для аналитиков SOC и защитников, в котором приватность на первом месте. Скопируйте IP-адрес, домен, URL или хеш файла и получите один понятный, объяснимый вердикт сразу от нескольких источников threat intelligence, не открывая десяток вкладок.

> 🚧 IOC Lens активно развивается. Функции и возможности будут расширяться и меняться со временем.

## 💡 Зачем нужен IOC Lens

Проверка подозрительного индикатора обычно означает: скопировать, открыть несколько сайтов, вставлять снова и снова и вручную сравнивать разные ответы. IOC Lens делает всё это одним действием.

| Без IOC Lens | С IOC Lens |
|---|---|
| Несколько вкладок, несколько входов в аккаунты | Одна быстрая проверка |
| Разные ответы, которые нужно сравнивать вручную | Один объединённый и объяснённый вердикт |
| Риск вставить внутренние данные на сторонние сайты | Внутренние индикаторы остаются на вашем компьютере |
| Тяжёлые платформы ради быстрой проверки | Небольшое и быстрое десктопное приложение |

## ✨ Главное

- ⚡ **Мгновенно**: проверка только что скопированного примерно за секунду
- 🌐 **Много источников, один ответ**: результаты нескольких бесплатных сервисов threat intelligence, объединённые и объяснённые
- 🔎 **Объяснимо**: каждый вердикт показывает, какие источники и почему повлияли на результат
- 🛡️ **Приватность прежде всего**: внутренние индикаторы никогда не покидают ваш компьютер
- 🔑 **Ваши ключи под вашим контролем**: без аккаунта, без телеметрии, ключи хранятся локально
- 🪶 **Лёгкое и нативное**: небольшое и быстрое, для **Windows, macOS и Linux**
- 🔓 **Открытый исходный код** под лицензией MIT

## 📥 Установка

1. Откройте **[страницу Releases](https://github.com/mr-coder20/ioc-lens/releases)**.
2. В разделе **Assets** последнего релиза скачайте файл для вашей системы:

   | Система | Файл |
   |---|---|
   | 🪟 Windows | `.msi` |
   | 🍎 macOS | `.dmg` |
   | 🐧 Linux | `.deb` |

3. Установите:

<details>
<summary><b>🪟 Windows</b></summary>

1. Запустите скачанный установщик `.msi` и следуйте мастеру.
2. Если **SmartScreen** выдаст предупреждение (ранние сборки могут быть без подписи), выберите **Подробнее → Выполнить в любом случае**.
3. Запустите **IOC Lens** из меню «Пуск». Приложение живёт в системном трее.

</details>

<details>
<summary><b>🍎 macOS</b></summary>

1. Откройте `.dmg` и перетащите **IOC Lens** в **Программы**.
2. При первом запуске, если macOS сообщит, что не может проверить разработчика, щёлкните по приложению **правой кнопкой → Открыть → Открыть**.
3. Выдайте разрешения, которые запросит macOS (например, для глобального сочетания клавиш).

</details>

<details>
<summary><b>🐧 Linux</b></summary>

1. Установите пакет:
   ```bash
   sudo apt install ./<скачанный-файл>.deb
   ```
2. Запустите **IOC Lens** из меню приложений.
3. В GNOME установите расширение *AppIndicator*, если не видите значок в трее.

</details>

> 🔐 **Рекомендуется:** сверьте SHA-256 скачанного файла с `SHA256SUMS.txt` из того же релиза.

## 🚀 Быстрый старт

1. Откройте **Settings → Providers** и добавьте **бесплатный API-ключ** хотя бы для одного источника (например, VirusTotal, AbuseIPDB, AlienVault OTX или abuse.ch URLhaus). Каждый сервис выдаёт бесплатные ключи на своём сайте.
2. Скопируйте любой индикатор: IP, домен, URL или хеш файла.
3. Используйте сочетание клавиш из Settings или нажмите на значок в трее. Вердикт появится через мгновение.

## 🎯 Как читать вердикты

| Вердикт | Значение |
|---|---|
| 🟢 **Clean** | Значимых обнаружений нет |
| 🟡 **Low risk** | Слабые или старые сигналы; оцените в контексте |
| 🟠 **Suspicious** | Заметные обнаружения; стоит проверить |
| 🔴 **Malicious** | Сильные обнаружения, подтверждённые несколькими источниками |
| ⚪ **No data** | Неизвестно включённым источникам. Это **не значит «безопасно»** |

Вердикт — помощник для триажа, а не окончательное суждение. Всегда применяйте собственный анализ.

## 🛡️ Приватность и безопасность

- **Privacy Guard** блокирует приватные и внутренние индикаторы (например, внутренние IP-адреса и внутренние доменные имена) до отправки чего-либо наружу. Можно добавить собственные правила.
- Отправляется только сам индикатор и только включённым вами источникам, по HTTPS.
- **Без бэкенда, без аккаунта, без телеметрии.**
- API-ключи хранятся в защищённом хранилище вашей операционной системы.
- Нашли уязвимость? Пожалуйста, прочитайте [SECURITY.md](SECURITY.md).

## ❓ Частые вопросы

<details>
<summary><b>IOC Lens бесплатный?</b></summary>

Да. Это проект с открытым кодом под лицензией MIT. Сервисы threat intelligence, к которым он обращается, предлагают бесплатные API-ключи; действуют их собственные условия и ограничения.
</details>

<details>
<summary><b>Мои данные отправляются вам?</b></summary>

Нет. Сервера IOC Lens не существует. Запросы идут напрямую с вашего компьютера к включённым вами источникам.
</details>

<details>
<summary><b>Почему «No data», а не «Clean»?</b></summary>

Отсутствие в базе данных не означает безопасность. Новая инфраструктура часто неизвестна, поэтому IOC Lens никогда не выдаёт отсутствие результата за «чисто».
</details>

<details>
<summary><b>Сочетание клавиш не работает.</b></summary>

Возможно, то же сочетание занято другим приложением. Измените его в Settings или используйте значок в трее.
</details>

## 🤝 Участие в проекте

Идеи, отчёты об ошибках, новые источники, переводы и код — всё приветствуется. Прочитайте [CONTRIBUTING.md](CONTRIBUTING.md), создайте [issue](https://github.com/mr-coder20/ioc-lens/issues) или отправьте pull request.

**⭐ Если IOC Lens экономит ваше время, звезда поможет другим защитникам его найти.**

Распространяется по [лицензии MIT](LICENSE).

<div align="right"><a href="#russian">↑ Наверх</a></div>

---

<div align="center">

[![Star History Chart](https://api.star-history.com/svg?repos=mr-coder20/ioc-lens&type=Date)](https://star-history.com/#mr-coder20/ioc-lens&Date)

**Made with ❤️ for defenders**

</div>
