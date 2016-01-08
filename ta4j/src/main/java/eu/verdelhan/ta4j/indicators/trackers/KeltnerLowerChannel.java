/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2015 Marc de Verdelhan & respective authors (see AUTHORS)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.verdelhan.ta4j.indicators.trackers;

import eu.verdelhan.ta4j.Decimal;
import eu.verdelhan.ta4j.TimeSeries;
import eu.verdelhan.ta4j.indicators.CachedIndicator;
import eu.verdelhan.ta4j.indicators.helpers.AverageTrueRangeIndicator;
import eu.verdelhan.ta4j.indicators.simple.TypicalPriceIndicator;

/**
 * Keltner Lower Channel
 */
public class KeltnerLowerChannel extends CachedIndicator<Decimal> {

	private final AverageTrueRangeIndicator averageTrueRangeIndicator;
	
	private final EMAIndicator emaIndicator;
	
	private final Decimal ratio;
	
	public KeltnerLowerChannel(TimeSeries series, int timeFrameEMA, Decimal ratio, int timeFrameATR) {
		super(series);
		this.ratio = ratio;
		emaIndicator = new EMAIndicator(new TypicalPriceIndicator(series), timeFrameEMA);
		averageTrueRangeIndicator = new AverageTrueRangeIndicator(series, timeFrameATR);
	}

	@Override
	protected Decimal calculate(int index) {
		return emaIndicator.getValue(index).minus(ratio.multipliedBy(averageTrueRangeIndicator.getValue(index)));
	} 
	
}
