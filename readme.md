![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=NickLib%20&fontSize=90&animation=fadeIn&fontAlignY=38&desc=%20%20%20%20%20%20%20&descAlignY=51&descAlign=62)

<p align='center'> 📚 『마인크래프트』 NickLib API 플러그인 📚 </p>
<p align='center'>
  <a href="https://github.com/idkNicks">
    <img src="https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white">
  </a>
  <a href="https://discord.com/users/992342653255557230">
    <img src="https://img.shields.io/badge/-Contact-ed8a6c?style=for-the-badge">
  </a>
  <a href="https://discord.gg/7eV6KxPdcQ">
    <img src="https://img.shields.io/badge/Discord-%235865F2.svg?style=for-the-badge&logo=discord&logoColor=white">
  </a>
</p>
<hr>

## ⚙️ 개발 환경 ⚙️
- 💡 NickLib을 사용하기 위해선 NickLib 플러그인을 같이 넣어주셔야 합니다.
- 📡 JDK(Java Development Kit) 17
- 🧭 SPIGOT 1.19.2
- 📩 버그 문의: 대영#1234

###
## 📑 콘피그 매니저 사용 방법 📑
###
- 📌 기본 콘피그를 생성하는 방법 📌
```java

Config config = new Config("path", JavaPlugin); // ".yml"는 제외합니다.
config.loadDefaultConfig();
```

###
- 📦 콘피그에 변수를 설정하는 방법 📦
```java

Config config = new Config("config", JavaPlugin);

config.setString("test", "테스트 문자열 입니다.");
config.setInteger("money", 300);
config.setDouble("weight", 60.5);
config.setBoolean("isMarried", true);

```
###
- 📦 콘피그에서 변수를 가져오는 방법 📦
```java

Config config = new Config("config", JavaPlugin);

config.getString("test");
config.getInteger("money");
config.getDouble("weight");
config.getBoolean("isMarried");

```
###
- 📦 OTHERS 📦
```java

Config config=new Config("config",JavaPlugin);

// 콘피그를 저장합니다.
config.saveConfig();

// 콘피그를 리로드합니다.
config.reloadConfig();

// 콘피그 파일을 삭제합니다.
config.delteFile();

// 콘피그가 존재하는지 체크합니다.
if(config.isFileExist()) {}

// 콘피그 목록을 가져옵니다.
config.fileListName();

```
<hr>

## 💾 인벤토리 유틸 사용 방법 💾
###
- 📫 콘피그에 아이템 저장 방법 📫
```java

@Eventhandler
public void onClose(InventoryCloseEvent event) {
    
    InventoryUtil inventoryUtil = new InventoryUtil(player, new Config("kit", JavaPlugin));
    inventoryUtil.saveInventory("defaultkit", event, event.getInventory());
}

// 위 코드를 사용할 시 저장 되는 형식
defaultkit:
  items: {}

```
###
- 🧩 콘피그에 있는 아이템을 불러오는 방법 🧩
```java

/** "기본템 설정"이라는 타이틀의 인벤토리를 자동으로 엽니다. */

InventoryUtil inventoryUtil = new InventoryUtil(player, new Config("kit", JavaPlugin));

inventoryUtil.getInventory("defaultkit", "기본템 설정", 4);

```