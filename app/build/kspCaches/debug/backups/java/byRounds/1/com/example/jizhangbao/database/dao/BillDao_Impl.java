package com.example.jizhangbao.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.jizhangbao.database.converters.DateTypeConvert;
import com.example.jizhangbao.database.entity.BillEntity;
import com.example.jizhangbao.model.RecordType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class BillDao_Impl implements BillDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BillEntity> __insertionAdapterOfBillEntity;

  private final DateTypeConvert __dateTypeConvert = new DateTypeConvert();

  private final EntityDeletionOrUpdateAdapter<BillEntity> __deletionAdapterOfBillEntity;

  public BillDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBillEntity = new EntityInsertionAdapter<BillEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `bill` (`id`,`user_id`,`money`,`remark`,`type`,`date`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BillEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getUserId());
        statement.bindDouble(3, entity.getMoney());
        statement.bindString(4, entity.getRemark());
        statement.bindString(5, __RecordType_enumToString(entity.getType()));
        final long _tmp = __dateTypeConvert.dateToLong(entity.getDate());
        statement.bindLong(6, _tmp);
      }
    };
    this.__deletionAdapterOfBillEntity = new EntityDeletionOrUpdateAdapter<BillEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `bill` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final BillEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final BillEntity billEntity, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfBillEntity.insert(billEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final BillEntity billEntity,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfBillEntity.handle(billEntity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object selectAll(final int userId,
      final Continuation<? super List<BillEntity>> $completion) {
    final String _sql = "select * from bill where user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<BillEntity>>() {
      @Override
      @NonNull
      public List<BillEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfMoney = CursorUtil.getColumnIndexOrThrow(_cursor, "money");
          final int _cursorIndexOfRemark = CursorUtil.getColumnIndexOrThrow(_cursor, "remark");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<BillEntity> _result = new ArrayList<BillEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BillEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final double _tmpMoney;
            _tmpMoney = _cursor.getDouble(_cursorIndexOfMoney);
            final String _tmpRemark;
            _tmpRemark = _cursor.getString(_cursorIndexOfRemark);
            final RecordType _tmpType;
            _tmpType = __RecordType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final Date _tmpDate;
            final long _tmp;
            _tmp = _cursor.getLong(_cursorIndexOfDate);
            _tmpDate = __dateTypeConvert.longToDate(_tmp);
            _item = new BillEntity(_tmpId,_tmpUserId,_tmpMoney,_tmpRemark,_tmpType,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object selectBillsByDate(final int userId, final Date startDate, final Date endDate,
      final Continuation<? super List<BillEntity>> $completion) {
    final String _sql = "SELECT * FROM bill WHERE date >= ? and date <= ? and user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    final long _tmp = __dateTypeConvert.dateToLong(startDate);
    _statement.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    final long _tmp_1 = __dateTypeConvert.dateToLong(endDate);
    _statement.bindLong(_argIndex, _tmp_1);
    _argIndex = 3;
    _statement.bindLong(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<BillEntity>>() {
      @Override
      @NonNull
      public List<BillEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
          final int _cursorIndexOfMoney = CursorUtil.getColumnIndexOrThrow(_cursor, "money");
          final int _cursorIndexOfRemark = CursorUtil.getColumnIndexOrThrow(_cursor, "remark");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
          final List<BillEntity> _result = new ArrayList<BillEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final BillEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final int _tmpUserId;
            _tmpUserId = _cursor.getInt(_cursorIndexOfUserId);
            final double _tmpMoney;
            _tmpMoney = _cursor.getDouble(_cursorIndexOfMoney);
            final String _tmpRemark;
            _tmpRemark = _cursor.getString(_cursorIndexOfRemark);
            final RecordType _tmpType;
            _tmpType = __RecordType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final Date _tmpDate;
            final long _tmp_2;
            _tmp_2 = _cursor.getLong(_cursorIndexOfDate);
            _tmpDate = __dateTypeConvert.longToDate(_tmp_2);
            _item = new BillEntity(_tmpId,_tmpUserId,_tmpMoney,_tmpRemark,_tmpType,_tmpDate);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __RecordType_enumToString(@NonNull final RecordType _value) {
    switch (_value) {
      case OTHER: return "OTHER";
      case DINING: return "DINING";
      case APPAREL: return "APPAREL";
      case EDUCATION: return "EDUCATION";
      case TRANSPORTATION: return "TRANSPORTATION";
      case MEDICAL: return "MEDICAL";
      case EXPRESS_DELIVERY: return "EXPRESS_DELIVERY";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private RecordType __RecordType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "OTHER": return RecordType.OTHER;
      case "DINING": return RecordType.DINING;
      case "APPAREL": return RecordType.APPAREL;
      case "EDUCATION": return RecordType.EDUCATION;
      case "TRANSPORTATION": return RecordType.TRANSPORTATION;
      case "MEDICAL": return RecordType.MEDICAL;
      case "EXPRESS_DELIVERY": return RecordType.EXPRESS_DELIVERY;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
