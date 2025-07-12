# コード規約 - Spring Boot API プロジェクト（レビュー観点）

## 1. 基本設計原則

### 1.1 責任分離
- **1エンドポイント1Controller/1Service** の原則を守る
- Controller → Service → Repository の依存関係を維持
- DTOとEntityを適切に分離

### 1.2 命名規則
- **Controller**: `{Entity}{Action}Controller` （例: `UserCreateController`）
- **Service**: `{Entity}{Action}Service` / `{Entity}{Action}ServiceImpl`
- **DTO**: `{Entity}{Action}RequestDto` / `{Entity}ResponseDto`
- **Exception**: `{Entity}{Type}Exception`

### 1.3 パッケージ構成
```
jp.ne.papapa.copilot_instructions/
├── controller/    # 1エンドポイント1クラス
├── service/       # 1操作1インターフェース
├── dto/          # リクエスト/レスポンス
├── entity/       # JPAエンティティ
├── repository/   # データアクセス
├── exception/    # 例外・例外ハンドラー
└── common/       # 定数・ユーティリティ
```

## 2. レビューチェックポイント

### 2.1 Controller層
- `@RestController`, `@RequiredArgsConstructor`, `@Slf4j` が付与されているか
- 1つのエンドポイントのみ実装されているか
- `@Valid` でリクエストバリデーションが行われているか
- Service層のみに依存し、Repository層に直接アクセスしていないか

### 2.2 Service層
- インターフェースと実装クラスが分離されているか
- `@Service`, `@RequiredArgsConstructor`, `@Transactional` が適切に付与されているか
- 1つのビジネス操作のみ実装されているか
- `ExceptionFactory` を使って例外を生成しているか
- DTOとEntityの変換が適切に行われているか

### 2.3 Repository層
- `JpaRepository<Entity, ID>`を継承しているか
- メソッド命名規則に従っているか（`findByXxx`, `existsByXxx`, `countByXxx`）
- 複雑な検索は`@Query`アノテーションを使用しているか

### 2.4 DTO設計
- `@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor` が付与されているか
- リクエストDTOにバリデーションアノテーションが適切に設定されているか
- メッセージキーが`messages.properties`で管理されているか
- Entity変換メソッド（`toEntity`, `fromEntity`）が実装されているか

### 2.5 Entity設計
- `@Entity`, `@Table`, `@Data`, `@Builder` が付与されているか
- カラム長が`Constants`で定数化されているか
- `@CreationTimestamp`, `@UpdateTimestamp`で日時管理されているか
- Enumは`@Enumerated(EnumType.STRING)`で保存されているか
- `nullable`, `unique`制約が適切に設定されているか

### 2.6 例外処理
- カスタム例外クラスが適切に定義されているか
- `ExceptionFactory`を使って例外を生成しているか
- `@RestControllerAdvice`でグローバル例外ハンドラーが実装されているか

## 3 開発・保守のポイント

### 3.1 新機能追加手順
1. Entity → Repository → DTO → Service → Controller の順で実装
2. 例外処理とメッセージ定義を忘れずに追加
3. テストコードも合わせて実装

### 3.2 禁止事項
- ControllerでのEntity直接使用
- Repository層でのビジネスロジック実装
- ハードコーディング（定数・メッセージの直書き）
- 1つのController/Serviceで複数機能実装
- 例外メッセージの直接記述

### 3.3 推奨事項
- `@RequiredArgsConstructor` でコンストラクタ注入
- `@Slf4j` でログ機能使用
- `@Builder` でオブジェクト生成
- メソッドチェーンでの可読性向上
- Optional型でNull安全性確保

## 4. その他開発ルール
- 想定外の例外処理（DBエラーなど）はControllerAdviceで行うので、個別での処理は不要
- 開始ログや終了ログはAspectで一括管理するので、個別での処理は不要
