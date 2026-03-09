import { useState } from 'react';
import { useOutletContext } from 'react-router-dom';
import { createRequest } from '../api/coachingRequests';

export default function CreateRequest() {
  const { memberId } = useOutletContext();
  const [trainerId, setTrainerId] = useState('2');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [done, setDone] = useState(false);

  const submit = async (e) => {
    e.preventDefault();
    setError('');
    setLoading(true);
    try {
      await createRequest(Number(memberId), Number(trainerId));
      setDone(true);
    } catch (err) {
      setError(err.message || '요청 생성 실패');
    } finally {
      setLoading(false);
    }
  };

  if (done) {
    return (
      <section className="card">
        <p className="success">코칭 요청이 생성되었습니다.</p>
        <p className="hint">내 요청 목록에서 확인하세요.</p>
      </section>
    );
  }

  return (
    <section className="card">
      <h2>1. 코칭 요청 생성</h2>
      <form onSubmit={submit}>
        <label>
          트레이너 ID
          <input
            type="number"
            min="1"
            value={trainerId}
            onChange={(e) => setTrainerId(e.target.value)}
            required
          />
        </label>
        {error && <p className="error">{error}</p>}
        <button type="submit" disabled={loading}>
          {loading ? '요청 중…' : '요청 보내기'}
        </button>
      </form>
    </section>
  );
}
