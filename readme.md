![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=NickLib%20&fontSize=90&animation=fadeIn&fontAlignY=38&desc=%20%20%20%20%20%20%20&descAlignY=51&descAlign=62)

<p align='center'> ๐ ใ๋ง์ธํฌ๋ํํธใ NickLib API ํ๋ฌ๊ทธ์ธ ๐ </p>
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

## โ๏ธ ๊ฐ๋ฐ ํ๊ฒฝ โ๏ธ
- ๐ก JDK(Java Development Kit) 17
- ๐งญ SPIGOT 1.19.2
- ๐ฉ ๋ฒ๊ทธ ๋ฌธ์: ๋์#1234
<hr>

## ๐ ์ฝํผ๊ทธ ๋งค๋์  ์ฌ์ฉ ๋ฐฉ๋ฒ ๐

- ๐ ๊ธฐ๋ณธ ์ฝํผ๊ทธ๋ฅผ ์์ฑํ๋ ๋ฐฉ๋ฒ ๐
```java

Config config = new Config("path", JavaPlugin); // ".yml"๋ ์ ์ธํฉ๋๋ค.
config.loadDefaultConfig();
```

###
- ๐ฆ ์ฝํผ๊ทธ์ ๋ณ์๋ฅผ ์ค์ ํ๋ ๋ฐฉ๋ฒ ๐ฆ
```java

Config config = new Config("config", JavaPlugin);

config.setString("test", "ํ์คํธ ๋ฌธ์์ด ์๋๋ค.");
config.setInteger("money", 300);
config.setDouble("weight", 60.5);
config.setBoolean("isMarried", true);

```

- ๐ฆ ์ฝํผ๊ทธ์์ ๋ณ์๋ฅผ ๊ฐ์ ธ์ค๋ ๋ฐฉ๋ฒ ๐ฆ
```java

Config config = new Config("config", JavaPlugin);

config.getString("test");
config.getInteger("money");
config.getDouble("weight");
config.getBoolean("isMarried");

```

- ๐ฆ OTHERS ๐ฆ
```java

Config config=new Config("config",JavaPlugin);

// ์ฝํผ๊ทธ๋ฅผ ์ ์ฅํฉ๋๋ค.
config.saveConfig();

// ์ฝํผ๊ทธ๋ฅผ ๋ฆฌ๋ก๋ํฉ๋๋ค.
config.reloadConfig();

// ์ฝํผ๊ทธ ํ์ผ์ ์ญ์ ํฉ๋๋ค.
config.deleteFile();

// ์ฝํผ๊ทธ๊ฐ ์กด์ฌํ๋์ง ์ฒดํฌํฉ๋๋ค.
if(config.isFileExist()) {}

// ์ฝํผ๊ทธ ๋ชฉ๋ก์ ๊ฐ์ ธ์ต๋๋ค.
config.fileListName();

```
<hr>

## ๐พ ์ธ๋ฒคํ ๋ฆฌ ์ ํธ ์ฌ์ฉ ๋ฐฉ๋ฒ ๐พ

- ๐ซ ์ฝํผ๊ทธ์ ์์ดํ ์ ์ฅ ๋ฐฉ๋ฒ ๐ซ
```java

@Eventhandler
public void onClose(InventoryCloseEvent event) {
    
    InventoryUtil inventoryUtil = new InventoryUtil(player, new Config("kit", JavaPlugin));
    inventoryUtil.saveInventory("defaultkit", event, event.getInventory());
}

// ์ ์ฝ๋๋ฅผ ์ฌ์ฉํ  ์ ์ ์ฅ ๋๋ ํ์
defaultkit:
  items: {}

```

- ๐งฉ ์ฝํผ๊ทธ์ ์๋ ์์ดํ์ ๋ถ๋ฌ์ค๋ ๋ฐฉ๋ฒ ๐งฉ
```java

/** "๊ธฐ๋ณธํ ์ค์ "์ด๋ผ๋ ํ์ดํ์ ์ธ๋ฒคํ ๋ฆฌ๋ฅผ ์๋์ผ๋ก ์ฝ๋๋ค. */

InventoryUtil inventoryUtil = new InventoryUtil(player, new Config("kit", JavaPlugin));

inventoryUtil.getInventory("defaultkit", "๊ธฐ๋ณธํ ์ค์ ", 4);

```
