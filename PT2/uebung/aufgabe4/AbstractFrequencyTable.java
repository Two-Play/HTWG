package aufgabe4;

import aufgabe4.FrequencyTable;

/**
 *
 * @author oliverbittel
 */
public abstract class AbstractFrequencyTable<T> implements FrequencyTable<T> {
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	@Override
    public void add(T w) {
		add(w, 1);
    }

	@Override
	public void addAll(FrequencyTable fq) {
		// Ihr Code:
		for (int i = 0; i < fq.size(); i++) {
			add((T) fq.get(i).getElement(), fq.get(i).getFrequency());
		}
	}

	@Override
	public void collectNMostFrequent(int n, FrequencyTable fq) {
		// Ihr Code:
		fq.clear();

		if (this.size() <= n) {
			fq.addAll(this);
			return;
		}

		for (int i = 0; i < n; i++) {
			fq.add(get(i).getElement(), get(i).getFrequency());
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder("{");
		// Ihr Code:
		for (int i = 0; i < size(); i++) {
			s.append(get(i).getElement()).append(":").append(get(i)
					.getFrequency()).append(", ");
		}
		s.append("} size = ").append(size());
		return s.toString();
	}
}
