import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';

export default function Home() {
  const [role, setRole] = useState('member');
  const [memberId, setMemberId] = useState('1');
  const [trainerId, setTrainerId] = useState('2');
  const navigate = useNavigate();

  const goAsMember = () => {
    navigate(`/member/${memberId}`);
  };

  const goAsTrainer = () => {
    navigate(`/trainer/${trainerId}`);
  };

  return (
    <div className="page">
      <h1>Fitmotion</h1>
      <p className="sub">역할과 ID를 선택한 뒤 시작하세요.</p>

      <section className="card">
        <h2>회원으로 시작</h2>
        <label>
          내 회원 ID
          <input
            type="number"
            min="1"
            value={memberId}
            onChange={(e) => setMemberId(e.target.value)}
          />
        </label>
        <button type="button" onClick={goAsMember}>
          회원 화면으로
        </button>
        <p className="hint">요청 생성 · 내 요청 목록 · 채팅</p>
      </section>

      <section className="card">
        <h2>트레이너로 시작</h2>
        <label>
          내 트레이너 ID
          <input
            type="number"
            min="1"
            value={trainerId}
            onChange={(e) => setTrainerId(e.target.value)}
          />
        </label>
        <button type="button" onClick={goAsTrainer}>
          트레이너 화면으로
        </button>
        <p className="hint">받은 요청 목록 · 수락/거절 · 채팅</p>
      </section>
    </div>
  );
}
