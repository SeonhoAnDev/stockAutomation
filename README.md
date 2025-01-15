# 株式自動化プロジェクト

## プロジェクト概要
このプロジェクトは、株式データを自動で収集し、Slackに通知を送信するシステムです。株式データを収集してレポートを作成し、これをSlackチャンネルに投稿することで、リアルタイムで株式情報を確認できます。

## 主な機能
- **株式データ収集**: 特定の株式のデータを収集して保存します。
- **営業日計算**: 前営業日を計算してデータを処理します。
- **Slack通知**: 収集した株式データをSlackチャンネルに通知します。
- **レポート保存**: 収集したデータに基づいてレポートを作成し保存します。

## 技術スタック
- **Java**: 主なプログラミング言語
- **Spring Boot**: アプリケーションフレームワーク
- **Gradle**: ビルドツール
- **JUnit**: テストフレームワーク
- **Mockito**: モッキングフレームワーク
- **Slack API**: Slack通知用API

# 株式自動化プロジェクト設計

## クラス設計
  
### StockSlackService
- **説明**: 株式データを収集し、Slackに通知を送信するサービスクラスです。
- **主なメソッド**:
    - `execute(String stock)`: 指定された株式コードに基づいてデータを収集し、Slackに通知を送信します。

### StockHttpClient
- **説明**: 株式データを外部APIから取得するHTTPクライアントクラスです。
- **主なメソッド**:
    - `getTickerByStock(String stock)`: 指定された株式コードに対する株式データを取得します。

### SlackHttpClient
- **説明**: Slackにメッセージを送信するHTTPクライアントクラスです。
- **主なメソッド**:
    - `sendSlackMessage(String message)`: 指定されたメッセージをSlackチャンネルに送信します。

### BusinessDayService
- **説明**: 営業日を計算するサービスクラスです。
- **主なメソッド**:
    - `getPreviousBusinessDay()`: 前営業日を計算して返します。

### ReportHistoryRepository
- **説明**: 株式データレポートを保存するリポジトリクラスです。
- **主なメソッド**:
    - `save(ReportHistory reportHistory)`: 指定された `ReportHistory` オブジェクトを保存します。

## API設計

### 株式データ収集API
- **エンドポイント**: `/api/stock/{stockCode}`
- **メソッド**: GET
- **説明**: 指定された株式コードに基づいて株式データを収集し、Slackに通知を送信します。
- **リクエストパラメータ**:
    - `stockCode` (String): 株式コード
- **レスポンス**:
    - 成功時: 200 OK
    - 失敗時: 400 Bad Request または 500 Internal Server Error

## データベース設計

### テーブル: `report_histories`
- **説明**: 株式データレポートを保存するテーブルです。
- **スキーマ**:
  ```sql
  CREATE TABLE `report_histories` (
      `id` INT AUTO_INCREMENT PRIMARY KEY,
      `stock_code` VARCHAR(45) NOT NULL,
      `open_price` VARCHAR(45) NOT NULL,
      `close_price` VARCHAR(45) NOT NULL,
      `high_price` VARCHAR(45) NOT NULL,
      `low_price` VARCHAR(45) NOT NULL,
      `reported_at` DATETIME
  );
