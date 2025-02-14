# 株式自動化システム

## 🎯 プロジェクト概要
株式市場のデータを収集し、自動的にSlackへ通知を送信する自動化システムを開発しました。

## 🌟 主な機能
- **株式データ収集**
  - 指定した銘柄の株価情報を自動収集
  - 株価の始値、終値、高値、低値などの詳細データを取得

- **インテリジェントな営業日管理**
  - 休日や祝日を考慮した営業日カレンダー管理
  - 前営業日のデータ自動計算機能

- **Slack連携による通知システム**
  - カスタマイズ可能なSlackチャンネルへの自動通知
  - 重要な株価変動のアラート機能

- **データ永続化と分析**
  - 収集したデータのデータベース保存
  - 過去データの分析とレポート生成機能

## 🛠 技術スタック
### バックエンド
- **言語**: Java 17
- **フレームワーク**: Spring Boot 3.4.1
- **ビルドツール**: Gradle
- **データベース**: MySQL

### 外部API連携
- Slack API
- 株式情報API

### テスト
- JUnit
- Mockito

### その他のツール
- Lombok
- Spring Batch

## 📊 システム設計

### アーキテクチャ
```
[株式データAPI] ⟶ [StockHttpClient] ⟶ [StockSlackService] ⟶ [SlackHttpClient] ⟶ [Slack]
                                          ↓
                                    [データベース]
```

### 主要コンポーネント
- **StockSlackService**: ビジネスロジックの中核
- **StockHttpClient**: 外部APIとの通信
- **BusinessDayService**: 営業日計算ロジック
- **ReportHistoryRepository**: データ永続化

## 💡 技術的な特徴
- **スケーラビリティ**
  - マイクロサービスアーキテクチャの採用
  - 非同期処理による効率的なデータ収集

- **保守性**
  - クリーンアーキテクチャの採用
  - 詳細なログ記録
  - 包括的なユニットテスト

- **セキュリティ**
  - 環境変数による機密情報の管理
  - APIキーの安全な取り扱い

## 🚀 今後の展望
- AIを活用した株価予測機能の追加
- より詳細な分析レポートの生成
- リアルタイムグラフ表示機能の実装
- finConnectアプリとの連携